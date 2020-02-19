package com.ispong.oxygen.flysql.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * sql statement properties
 *
 * @author ispong
 * @since  0.0.1
 */
@Data
public class SqlStatement {

    /**
     * selected column names
     */
    public List<String> columnNames = new ArrayList<>();

    /**
     * dateBase table name
     */
    public String tableName = "";

    /**
     * conditionValues
     */
    public List<SqlCondition> conditionValues = new ArrayList<>();

    /**
     * set values
     */
    public Map<String, String> setValues = new HashMap<>();

}
