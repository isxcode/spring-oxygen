package com.isxcode.ispring.code;

import com.isxcode.ispring.sql.SqlFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
                CodeInfo codeInfo = generateCodeInfo(tableName, fileType);
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
        // 文件夹,通过properties去获取文件夹路径
//        codeInfo.setDirectoryPath(codeProperties.getProjectPath() + codeProperties.getPaths().get(fileType + "-path"));

        // 文件路径
//        codeInfo.setFilePath();

        // 模板名称
        codeInfo.setTemplateName(tableName);

        // freemarker的信息
        List<TableColumn> tableColumns = SqlFactory.selectSql(TableColumn.class).sql("SHOW FULL COLUMNS FROM " + tableName).query();
        codeInfo.setTableColumns(tableColumns);

        return codeInfo;
    }

}
