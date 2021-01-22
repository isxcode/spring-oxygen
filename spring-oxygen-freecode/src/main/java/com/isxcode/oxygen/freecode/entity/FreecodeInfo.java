package com.isxcode.oxygen.freecode.entity;

import com.isxcode.oxygen.freecode.properties.FreecodeProperties;
import lombok.Data;

import java.util.List;

/**
 * freemarker all info
 *
 * @author ispong
 * @since 0.0.1
 */
@Data
public class FreecodeInfo {

    /**
     * table columns list
     */
    private List<TableColumnInfo> tableColumns;

    /**
     * entity class package
     */
    private List<String> entityPackageList;

    /**
     * table name
     */
    private String tableName;

    /**
     * table comment
     */
    private String tableComment;

    /**
     * class name
     */
    private String className;

    /**
     * package name
     */
    private String packageName;

    /**
     * freecdoe config
     */
    private FreecodeProperties freecodeProperties;

}
