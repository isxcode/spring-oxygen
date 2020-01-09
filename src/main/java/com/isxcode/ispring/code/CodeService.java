package com.isxcode.ispring.code;

import com.isxcode.ispring.sql.SqlFactory;
import com.isxcode.ispring.utils.AnnotationUtils;
import com.isxcode.ispring.utils.FormatUtils;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.thymeleaf.util.StringUtils.substring;

/**
 * 方法实现类
 *
 * @author ispong
 * @version v0.1.0
 * @date 2020-01-08
 */
@Service
public class CodeService {

    /**
     * 初始化即将生成的类型
     */
    private static List<String> fileTypeList = Arrays.asList("controller", "service", "entity", "dao");

    private final CodeProperties codeProperties;

    public CodeService(CodeProperties codeProperties) {

        this.codeProperties = codeProperties;
    }

    /**
     * 通过表名生成代码
     *
     * @param tableName 表名
     * @since 2020-01-08
     */
    public void generateCode(String tableName) {

        for (String fileType : fileTypeList) {
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

        CodeInfo codeInfo = new CodeInfo();

        // 模块名称 com.main.java+"."+com.isxcode.ispring
        String modulePath = codeProperties.getMainPath() + "." + codeProperties.getModulePath();
        // 文件夹路径 com.main.java+"."+com.isxcode.ispring+"."+"controller"
        String directoryPath = modulePath + "." + codeProperties.getFilePaths().get(fileType);
        // 文件名成 UserEntity
        String fileName = CodeUtils.upperStrFirst(AnnotationUtils.lineToHump(tableName)) + CodeUtils.upperStrFirst(fileType);
        // 文件后缀 .java/xml
        String fileSuffix = "." + CodeUtils.parseTemplateFileType(codeProperties.getTemplateFileList().get(fileType));
        // 文件夹路径
        codeInfo.setDirectoryPath(CodeUtils.parsePath(directoryPath));
        // 文件路径
        codeInfo.setFilePath(CodeUtils.parsePath(directoryPath) + fileName + fileSuffix);
        // 模板名称
        codeInfo.setTemplateName(codeProperties.getTemplateFileList().get(fileType));
        // 文件名称
        codeInfo.setClassName(fileName);
        // freemarker的信息'uuid','',''
        if ("entity".equals(fileType)) {
            StringBuilder ignoreField = new StringBuilder();
            for (String metaField : codeProperties.getIgnoreFields()) {
                ignoreField.append("'").append(metaField).append("',");
            }
            List<TableColumn> tableColumns = SqlFactory.selectSql(TableColumn.class).sql("SHOW FULL COLUMNS FROM " + tableName + " where Field not in (" + ignoreField.toString().substring(0, ignoreField.toString().length() - 1) + ")").query();
            codeInfo.setImportPackages(CodeUtils.generateImportPackages(tableColumns));
            codeInfo.setTableColumns(tableColumns);
            codeInfo.setTableName(tableName);
        }
        // 表注释
        TableColumn tableComment = SqlFactory.selectSql(TableColumn.class).sql("SELECT TABLE_COMMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = '" + tableName + "'").getOne();
        codeInfo.setTableComment(tableComment.getTableComment());
        // 作者
        codeInfo.setAuthor(codeProperties.getAuthor());
        // 导包
        codeInfo.setPackageName(codeProperties.getModulePath() + "." + codeProperties.getFilePaths().get(fileType));
        // 时间
        codeInfo.setDate(LocalDateTime.now());
        codeInfo.setTableHump(AnnotationUtils.lineToHump(tableName));
        return codeInfo;
    }

}
