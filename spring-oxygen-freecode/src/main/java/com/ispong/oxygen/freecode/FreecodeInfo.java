package com.ispong.oxygen.freecode;

import com.ispong.oxygen.freecode.model.TableColumnInfo;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * freemarker 属性对象
 *
 * @author ispong
 * @since 0.0.1
 */
@Data
public class FreecodeInfo {

    /**
     * 表字段信息
     */
    private List<TableColumnInfo> tableColumns;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 模型的package class
     */
    private List<String> entityPackageList;

    /**
     * 基础属性
     */
    private FreecodeProperties freecodeProperties;
}
