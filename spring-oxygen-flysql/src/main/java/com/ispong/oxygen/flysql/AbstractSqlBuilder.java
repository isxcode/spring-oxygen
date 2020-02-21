/*
 * Copyright [2020] [ispong]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ispong.oxygen.flysql;

import com.ispong.oxygen.flysql.model.SqlCondition;
import com.ispong.oxygen.flysql.model.SqlOperateType;
import com.ispong.oxygen.flysql.model.SqlType;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * for sql condition
 *
 * @author ispong
 * @version v0.1.0
 */
public abstract class AbstractSqlBuilder<T> {

    public final List<SqlCondition> sqlConditions = new ArrayList<>();

    private SqlType sqlType;

    private Map<String, String> columnsMap;

    public AbstractSqlBuilder(SqlType sqlType, Map<String, String> columnsMap) {

        this.sqlType = sqlType;
        this.columnsMap = columnsMap;
    }

    public abstract T getSelf();

    private String addSingleQuote(Object value) {

        return "'" + value + "'";
    }

    public T select(String... columnNames) {

        if (sqlType.equals(SqlType.VIEW_SELECT)) {
            sqlConditions.add(new SqlCondition(SqlOperateType.SELECT, "", Strings.join(Arrays.asList(columnNames), ',')));
        }else{
            List<String> columnNameList = new ArrayList<>(columnNames.length);
            for (String columnName : columnNames) {
                columnNameList.add(columnsMap.get(columnName) + " " + columnName);
            }
            sqlConditions.add(new SqlCondition(SqlOperateType.SELECT, "", Strings.join(columnNameList, ',')));
        }
        return getSelf();
    }

    public T setValue(String columnName, String name) {

        sqlConditions.add(new SqlCondition(SqlOperateType.SET_VALUE, ":" + columnName, "'" + name + "'"));
        return getSelf();
    }

    public T or() {

        sqlConditions.add(new SqlCondition(SqlOperateType.OR, "", ""));
        return getSelf();
    }

    public T and() {

        sqlConditions.add(new SqlCondition(SqlOperateType.AND, "", ""));
        return getSelf();
    }

    public T eq(String columnName, Object value) {

        sqlConditions.add(new SqlCondition(SqlOperateType.EQ, columnsMap.get(columnName), addSingleQuote(value)));
        return getSelf();
    }

    public T ne(String columnName, Object value) {

        sqlConditions.add(new SqlCondition(SqlOperateType.NE, columnsMap.get(columnName), addSingleQuote(value)));
        return getSelf();
    }

    public T gt(String columnName, Object value) {

        sqlConditions.add(new SqlCondition(SqlOperateType.EQ, columnsMap.get(columnName), addSingleQuote(value)));
        return getSelf();
    }

    public T gtEq(String columnName, Object value) {

        sqlConditions.add(new SqlCondition(SqlOperateType.GT_EQ, columnsMap.get(columnName), addSingleQuote(value)));
        return getSelf();
    }

    public T lt(String columnName, Object value) {

        sqlConditions.add(new SqlCondition(SqlOperateType.LT, columnsMap.get(columnName), addSingleQuote(value)));
        return getSelf();
    }

    public T ltEq(String columnName, Object value) {

        sqlConditions.add(new SqlCondition(SqlOperateType.LT_EQ, columnsMap.get(columnName), addSingleQuote(value)));
        return getSelf();
    }

    public T in(String columnName, Object... values) {

        List<String> inValues = new ArrayList<>();
        Arrays.stream(values).forEach(v -> inValues.add(addSingleQuote(v)));
        sqlConditions.add(new SqlCondition(SqlOperateType.IN, columnsMap.get(columnName), "(" + Strings.join(inValues, ',') + ")"));
        return getSelf();
    }

    public T notIn(String columnName, Object... values) {

        List<String> inValues = new ArrayList<>();
        Arrays.stream(values).forEach(v -> inValues.add(addSingleQuote(v)));
        sqlConditions.add(new SqlCondition(SqlOperateType.NOT_IN, columnsMap.get(columnName), "(" + Strings.join(inValues, ',') + ")"));
        return getSelf();
    }

    public T between(String columnName, Object value1, Object value2) {

        sqlConditions.add(new SqlCondition(SqlOperateType.BETWEEN, columnsMap.get(columnName), "(" + addSingleQuote(value1) + " and " + addSingleQuote(value2) + ")"));
        return getSelf();
    }

    public T orderBy(String columnName, String orderType) {

        sqlConditions.add(new SqlCondition(SqlOperateType.ORDER_BY, columnsMap.get(columnName), orderType));
        return getSelf();
    }

    public T like(String columnName, String value) {

        sqlConditions.add(new SqlCondition(SqlOperateType.LIKE, columnsMap.get(columnName), addSingleQuote("%" + value + "%")));
        return getSelf();
    }

    public T limit(Integer value) {

        sqlConditions.add(new SqlCondition(SqlOperateType.LIMIT, "", value));
        return getSelf();
    }

    public T update(String columnName, Object value) {

        sqlConditions.add(new SqlCondition(SqlOperateType.UPDATE, columnsMap.get(columnName), addSingleQuote(value)));
        return getSelf();
    }

    public T having(String... conditions) {

        return getSelf();
    }

    public T ge(){

        return getSelf();
    }

    public T le(){

        return getSelf();
    }

    public T notBetween(){

        return getSelf();
    }

    public T notLike(){

        return getSelf();
    }

    public T isNUll(){

        return getSelf();
    }

    public T isNotNull(){

        return getSelf();
    }

    public T sql(){

        return getSelf();
    }

    public T unSelect(){

        return getSelf();
    }

    public T count(){

        return getSelf();
    }

    public T distinct(){

        return getSelf();
    }

    public T groupBy() {

        return getSelf();
    }

}
