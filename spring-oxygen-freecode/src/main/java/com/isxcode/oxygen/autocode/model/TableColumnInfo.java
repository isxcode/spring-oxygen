package com.isxcode.oxygen.autocode.model;

import com.isxcode.oxygen.autocode.CodeUtils;
import com.isxcode.oxygen.core.reflect.ReflectUtils;
import lombok.Data;

/**
 * 数据库字段信息
 *
 * @author ispong
 * @version v0.1.0
 */
@Data
public class TableColumnInfo {

    /**
     * 字段名
     */
    private String field;

    /**
     * 字段类型
     */
    private String type;

    /**
     * 字符集
     */
    private String collation;

    /**
     * 主键
     */
    private String key;

    /**
     * 额外值
     */
    private String extra;

    /**
     * 权限
     */
    private String privileges;

    /**
     * 字段备注
     */
    private String comment;

    public String getType() {

        return CodeUtils.parseDataType(type);
    }

    public String getField() {

        return ReflectUtils.lineToHump(field);
    }
}
