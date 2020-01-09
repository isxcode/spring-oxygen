package com.isxcode.ispring.code;

import com.isxcode.ispring.utils.AnnotationUtils;
import lombok.Data;

/**
 * 数据库中的字段信息
 *
 * @author ispong
 * @version v0.1.0
 * @date 2020-01-09
 */
@Data
public class TableColumn {

    /**
     * 字段名
     */
    private String field;

    /**
     * 字段类型
     */
    private String type;

    /**
     * 字段备注
     */
    private String comment;

    /**
     * 字段需要导包
     */
    private String fieldPackage;

    private String tableComment;

    public String getType() {

        if (type.substring(0, 3).equals("var")) {
            return "String";
        } else if (type.substring(0, 3).equals("int")) {
            return "Integer";
        } else if (type.substring(0, 3).equals("dat")) {
            return "LocalDateTime";
        } else {
            return "";
        }

    }

    public String getField() {
        return AnnotationUtils.lineToHump(field);
    }

    public String getFieldPackage() {
        return "test";
    }
}
