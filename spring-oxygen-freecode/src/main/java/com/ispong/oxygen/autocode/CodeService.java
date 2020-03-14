package com.ispong.oxygen.autocode;

import com.ispong.oxygen.autocode.model.TableColumnInfo;
import com.ispong.oxygen.core.freemarker.FreemarkerUtils;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.ispong.oxygen.autocode.AutocodeProperties.ENTITY;

/**
 * 自动生成代码实现类
 *
 * @author ispong
 * @version v0.1.0
 */
@Service
public class CodeService {

    private final AutocodeProperties autoCodeProperties;

    private final CodeDao codeDao;

    public CodeService(CodeDao codeDao, AutocodeProperties autoCodeProperties) {

        this.codeDao = codeDao;
        this.autoCodeProperties = autoCodeProperties;
    }

    /**
     * 通过表名生成代码
     *
     * @param tableName 表名
     * @since 2020-01-08
     */
    public void generateCode(String tableName) {

        for (String fileType : autoCodeProperties.getFileTypeList()) {
            if (autoCodeProperties.getTemplateFileList().containsKey(fileType)) {
                // 通过表名和类型,生成文件的配置信息
                AutocodeInfo autoCodeInfo = generateCodeInfo(tableName, fileType);
                // 通过配置信息直接生成文件
                generateFile(autoCodeInfo);
            }
        }

    }

    /**
     * 封装对象CodeInfo
     *
     * @param tableName 表名
     * @param fileType  文件类型
     * @since 2020-01-08
     */
    private AutocodeInfo generateCodeInfo(String tableName, String fileType) {

        // 创建对象
        AutocodeInfo autoCodeInfo = new AutocodeInfo();
        autoCodeInfo.setFileType(fileType);
        autoCodeInfo.setAutoCodeProperties(autoCodeProperties);

        // 如果是entity类型文件,插入数据库字段信息
        if (ENTITY.equals(fileType)) {
            autoCodeInfo.setTableName(tableName);
            List<TableColumnInfo> tableColumns = codeDao.getTableColumns(tableName, autoCodeProperties.getIgnoreFields());
            autoCodeInfo.setFieldList(tableColumns);
            autoCodeInfo.setImportPackages(CodeUtils.generateImportPackages(tableColumns));
        }

        // 数据库表注释
        autoCodeInfo.setTableComment(codeDao.getTableInfo(tableName).getTableComment());

        // 文件名
        autoCodeInfo.setClassName(CodeUtils.lineToHump(tableName));

        return autoCodeInfo;
    }


    /**
     * 自动生成代码文件
     *
     * @param autoCodeInfo 自动生成代码内容
     * @since 2020-01-08
     */
    public void generateFile(AutocodeInfo autoCodeInfo) {

        String directoryPathStr = autoCodeProperties.getMainPath() + "." + autoCodeProperties.getModulePath() + "." + autoCodeProperties.getFilePaths().get(autoCodeInfo.getFileType());
        String filePathStr = CodeUtils.parseClassPathToPath(directoryPathStr) + CodeUtils.upperFirstCase(autoCodeInfo.getClassName()) + CodeUtils.upperFirstCase(autoCodeInfo.getFileType()) + "." + CodeUtils.parseTemplateFileType(autoCodeProperties.getTemplateFileList().get(autoCodeInfo.getFileType()));

        try {
            // 生成文件夹
            Path directoryPath = Paths.get(CodeUtils.parseClassPathToPath(directoryPathStr));
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }
            // 生成文件
            Path filePath = Paths.get(filePathStr);
            if (!Files.exists(filePath)) {
                Path path = Files.createFile(filePath);
                String codeContent = FreemarkerUtils.getFreemarkerStr(autoCodeProperties.getTemplateFileList().get(autoCodeInfo.getFileType()), autoCodeInfo);
                Files.writeString(path, codeContent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new AutocodeException("文件生成失败");
        }
    }

}
