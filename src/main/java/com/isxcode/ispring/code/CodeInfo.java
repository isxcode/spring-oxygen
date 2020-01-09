package com.isxcode.ispring.code;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 给freemarker配置的文件
 *
 * @author ispong
 * @version v0.1.0
 * @date 2020-01-08
 */
@Data
public class CodeInfo {

    /**
     * 需要生成的文件夹地址
     */
    private String directoryPath;

    /**
     * 需要生成的文件地址
     */
    private String filePath;

    /**
     * 模板的名称
     */
    private String templateName;

    /**
     * 数据库字段列表
     */
    private List<TableColumnInfo> fieldList;

    /**
     * 文件名(userEntity)
     */
    private String className;

    /**
     * 数据库备注
     */
    private String tableComment;

    /**
     * 作者
     */
    private String author;

    /**
     * 创建时间
     */
    private LocalDateTime date;

    /**
     * 包名 com.isxcode.ispring.model.entity
     */
    private String packageName;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 需要导入的包
     */
    private List<String> importPackages;

    /**
     * 基础类
     */
    private Map<String, String> baseClassList;

}
