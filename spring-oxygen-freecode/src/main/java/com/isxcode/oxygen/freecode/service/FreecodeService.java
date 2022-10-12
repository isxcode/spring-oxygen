package com.isxcode.oxygen.freecode.service;

import com.isxcode.oxygen.core.reflect.ReflectUtils;
import com.isxcode.oxygen.freecode.constant.FreecodeConstants;
import com.isxcode.oxygen.freecode.entity.FreecodeInfo;
import com.isxcode.oxygen.freecode.exception.FreecodeException;
import com.isxcode.oxygen.freecode.properties.FreecodeProperties;
import com.isxcode.oxygen.freecode.repository.FreecodeRepository;
import com.isxcode.oxygen.freecode.utils.FreecodeUtils;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

/**
 * freecode service
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
public class FreecodeService {

	private final FreecodeRepository freecodeRepository;

	private final FreecodeProperties freecodeProperties;

	public FreecodeService(
			FreecodeRepository freecodeRepository, FreecodeProperties freecodeProperties) {

		this.freecodeProperties = freecodeProperties;
		this.freecodeRepository = freecodeRepository;
	}

	/**
	 * start generate code
	 *
	 * @param tableNames table names
	 * @since 0.0.1
	 */
	public void startFreecode(String tableNames) {

		for (String metaTableName : tableNames.split(FreecodeConstants.splitStr)) {

			metaTableName = metaTableName.trim();

			// generate freemarker info
			FreecodeInfo freecodeInfo = generateFreecodeInfo(metaTableName);

			// pojo name
			String pojoName =
					ReflectUtils.lineToHump(FreecodeUtils.parseTableName(metaTableName, freecodeProperties));

			// class name
			String className = ReflectUtils.upperFirstCase(pojoName);

			// directory name
			String directoryName = pojoName.toLowerCase();

			freecodeInfo.setClassName(className);
			freecodeInfo.setPackageName(
					FreecodeUtils.parsePathToPackage(freecodeProperties.getModulePath())
							+ "."
							+ directoryName);

			// foreach generate file
			for (String fileType : freecodeProperties.getFileTypes()) {

				// templateName
				String templateName = fileType + freecodeProperties.getTemplatePrefix();

				// fileName
				String fileName =
						className + ReflectUtils.upperFirstCase(fileType) + FreecodeConstants.JAVA_FILE_SUFFIX;

				// generate file
				try {
					FreecodeUtils.generateFile(directoryName, fileName, templateName, freecodeInfo);
				} catch (IOException e) {
					log.error(e.getMessage());
					throw new FreecodeException(e.getMessage());
				}
			}
		}
	}

	/**
	 * create freemarker params
	 *
	 * @param tableName tableName
	 * @return FreecodeInfo
	 * @since 0.0.1
	 */
	public FreecodeInfo generateFreecodeInfo(String tableName) {

		FreecodeInfo freecodeInfo = new FreecodeInfo();

		// add table columns info
		freecodeInfo.setTableColumns(
				freecodeRepository.getTableColumns(tableName, freecodeProperties.getIgnoreColumns()));

		// class package list
		freecodeInfo.setEntityPackageList(
				FreecodeUtils.parseDataPackage(freecodeInfo.getTableColumns()));

		// add user config
		freecodeInfo.setFreecodeProperties(freecodeProperties);

		// table name
		freecodeInfo.setTableName(tableName);

		// add column comment
		freecodeInfo.setTableComment(freecodeRepository.getTableInfo(tableName));

		return freecodeInfo;
	}
}
