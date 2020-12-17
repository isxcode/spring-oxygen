package com.isxcode.oxygen.freecode.entity;

import com.isxcode.oxygen.core.reflect.ReflectUtils;
import com.isxcode.oxygen.freecode.utils.FreecodeUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * table column info
 *
 * @author ispong
 * @version v0.1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableColumnInfo {

    /**
     * field
     */
    private String field;

    /**
     * type
     */
    private String type;

    /**
     * collation
     */
    private String collation;

    /**
     * key
     */
    private String key;

    /**
     * extra
     */
    private String extra;

    /**
     * privileges
     */
    private String privileges;

    /**
     * comment
     */
    private String comment;

    /**
     * get data type
     *
     * @return data type
     */
    public String getType() {

        return FreecodeUtils.parseDataType(this.type);
    }

    /**
     * get data field
     *
     * @return data field
     */
    public String getField() {

        return ReflectUtils.lineToHump(this.field);
    }

}
