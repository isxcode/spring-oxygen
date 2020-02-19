package com.ispong.oxygen.flysql.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * 
 * @author ispong
 * @since  0.0.1
 */
@Data
@AllArgsConstructor
public class SqlCondition {

    private String conditionType;

    private String columnName;

    private String value;
}
