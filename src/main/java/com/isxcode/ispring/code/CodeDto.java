package com.isxcode.ispring.code;

import lombok.Data;

import java.util.List;

@Data
public class CodeDto {

    /**
     * 表名
     */
    private String tableName;

    /**
     * sql
     */
    private String sqlStr;

    /**
     * path
     */
    private List<String> path;

}
