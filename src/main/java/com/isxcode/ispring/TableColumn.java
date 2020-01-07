package com.isxcode.ispring;

import lombok.Data;

@Data
public class TableColumn {

    private String field;

    private String type;

    private String comment;

    private String fieldPackage;

    public String getField() {

        return field;
    }

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

    public String getComment() {
        return comment;
    }
}
