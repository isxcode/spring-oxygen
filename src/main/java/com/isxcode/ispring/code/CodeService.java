package com.isxcode.ispring.code;

import com.isxcode.ispring.utils.AnnotationUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.isxcode.ispring.code.CodeProperties.ENTITY;

/**
 * 自动生成代码实现类
 *
 * @author ispong
 * @version v0.1.0
 * @date 2020-01-08
 */
@Service
public class CodeService {

    private final CodeProperties codeProperties;

    private final CodeDao codeDao;

    public CodeService(CodeDao codeDao, CodeProperties codeProperties) {

        this.codeDao = codeDao;
        this.codeProperties = codeProperties;
    }

    /**
     * 通过表名生成代码
     *
     * @param tableName 表名
     * @since 2020-01-08
     */
    public void generateCode(String tableName) {

        for (String fileType : codeProperties.getFileTypeList()) {
            if (codeProperties.getTemplateFileList().containsKey(fileType)) {
                // 通过表名和类型,生成文件的配置信息
                CodeInfo codeInfo = generateCodeInfo(tableName, fileType);
                // 通过配置信息直接生成文件
                CodeUtils.generateFile(codeInfo);
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
    private CodeInfo generateCodeInfo(String tableName, String fileType) {

        // 创建对象
        CodeInfo codeInfo = new CodeInfo();

        // 作者名/时间
        codeInfo.setAuthor(codeProperties.getAuthor());
        codeInfo.setDate(LocalDateTime.now());

        // 如果是entity类型文件,插入数据库字段信息
        if (ENTITY.equals(fileType)) {
            codeInfo.setTableName(tableName);
            List<TableColumnInfo> tableColumns = codeDao.getTableColumns(tableName, codeProperties.getIgnoreFields());
            codeInfo.setFieldList(tableColumns);
            codeInfo.setImportPackages(CodeUtils.generateImportPackages(tableColumns));
        }

        // 数据库表注释
        codeInfo.setTableComment(codeDao.getTableInfo(tableName).getTableComment());

        // 文件地址/文件夹地址/模板名称
        String modulePath = codeProperties.getMainPath() + "." + codeProperties.getModulePath();
        String directoryPath = modulePath + "." + codeProperties.getFilePaths().get(fileType);
        String fileName = CodeUtils.upperStrFirst(AnnotationUtils.lineToHump(tableName)) + CodeUtils.upperStrFirst(fileType);
        codeInfo.setDirectoryPath(directoryPath);
        codeInfo.setFilePath(CodeUtils.parseClassPathToPath(directoryPath) + fileName + "." + CodeUtils.parseTemplateFileType(codeProperties.getTemplateFileList().get(fileType)));
        codeInfo.setTemplateName(codeProperties.getTemplateFileList().get(fileType));
        codeInfo.setClassName(fileName);
        codeInfo.setPackageName(codeProperties.getModulePath() + "." + codeProperties.getFilePaths().get(fileType));
        codeInfo.setBaseClassList(codeProperties.getBaseClassList());

        return codeInfo;
    }

}
