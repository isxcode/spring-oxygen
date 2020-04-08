package com.ispong.oxygen.freecode.model;

import com.ispong.oxygen.freecode.FreecodeUtils;
import lombok.Data;

/**
 * 数据库表每个字段的所有信息对象
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

        return FreecodeUtils.parseDataType(type);
    }

    public String getField() {

        return FreecodeUtils.lineToHump(field);
    }

}
