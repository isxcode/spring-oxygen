package com.isxcode.oxygen.freecode.utils;

import static java.util.regex.Pattern.compile;

import com.isxcode.oxygen.core.freemarker.FreemarkerUtils;
import com.isxcode.oxygen.freecode.entity.FreecodeInfo;
import com.isxcode.oxygen.freecode.entity.TableColumnInfo;
import com.isxcode.oxygen.freecode.properties.FreecodeProperties;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

/**
 * freecode utils
 *
 * @author ispong
 * @since 0.0.1
 */
@Component
@Slf4j
public class FreecodeUtils {

	/**
	 * generate file
	 *
	 * @param directoryName directory name
	 * @param fileName file name
	 * @param templateName template name
	 * @param freecodeInfo freecodeInfo
	 * @throws IOException file exception
	 * @since 0.0.1
	 */
	public static void generateFile(
			String directoryName, String fileName, String templateName, FreecodeInfo freecodeInfo)
			throws IOException {

		// fine module path
		String modulePath =
				ResourceUtils.getURL(freecodeInfo.getFreecodeProperties().getModulePath())
						.getPath()
						.substring(1);

		// generate directoty
		String directoryPath = modulePath + directoryName;
		if (!Files.exists(Paths.get(directoryPath)) && !Files.isDirectory(Paths.get(directoryPath))) {
			Files.createDirectory(Paths.get(directoryPath));
		}
		// geneate file
		String filePath = directoryPath + "/" + fileName;
		if (!Files.exists(Paths.get(filePath))) {
			FreemarkerUtils.templateToFile(templateName, freecodeInfo, filePath);
		}
	}

	/**
	 * parse data type
	 *
	 * @param dataType dataType
	 * @return data class
	 * @since 2020-01-09
	 */
	public static String parseDataType(String dataType) {

		Pattern pattern = compile("\\(.*?\\)");
		String dataStr = pattern.matcher(dataType).replaceAll("");

		switch (dataStr.toLowerCase()) {
			case "int":
			case "integer":
				return "Integer";
			case "bigint":
				return "Long";
			case "datetime":
			case "timestamp":
				return "LocalDateTime";
			case "date":
				return "LocalDate";
			case "double":
				return "Double";
			case "decimal":
				return "BigDecimal";
			default:
				return "String";
		}
	}

	/**
	 * parse table name
	 *
	 * @param tableName tableName
	 * @param freecodeProperties freecodeProperties
	 * @return String
	 * @since 0.0.1
	 */
	public static String parseTableName(String tableName, FreecodeProperties freecodeProperties) {

		if (freecodeProperties.getTablePrefix() == null) {
			return tableName;
		} else {
			return tableName.replaceFirst(freecodeProperties.getTablePrefix(), "");
		}
	}

	/**
	 * parse entity properties class
	 *
	 * @param columnInfoList columnInfoList
	 * @return List[String]
	 * @since 2020-01-09
	 */
	public static List<String> parseDataPackage(List<TableColumnInfo> columnInfoList) {

		List<String> packages = new ArrayList<>();

		for (TableColumnInfo metaColumn : columnInfoList) {

			switch (metaColumn.getType()) {
				case "LocalDateTime":
					packages.add("java.time.LocalDateTime");
					break;
				case "LocalDate":
					packages.add("java.time.LocalDate");
					break;
				case "BigDecimal":
					packages.add("java.math.BigDecimal");
					break;
				case "Long":
					packages.add("java.lang.Long");
					break;
				default:
			}
		}

		return packages.stream().distinct().collect(Collectors.toList());
	}

	/**
	 * turn moudle path to java package
	 *
	 * @param moudlePath moudlePath
	 * @return String
	 * @since 0.0.1
	 */
	public static String parsePathToPackage(String moudlePath) {

		String[] split = moudlePath.split("/");
		int len = split[0].length() + split[1].length() + split[2].length() + 3;
		return moudlePath.substring(len).replace("/", ".");
	}

	/**
	 * 获取用户默认数据库类型
	 *
	 * @return database
	 * @since 0.0.1
	 */
	public static String getDataBaseType() {

		// get database type
		//        try {
		// todo 修复freecode
		//            return
		// Flysql.getDefaultDataSource().getConnection().getMetaData().getDatabaseProductName();
		return "";
		//        } catch (SQLException throwables) {
		//            throw new OxygenException(throwables.getMessage());
		//        }
	}
}
