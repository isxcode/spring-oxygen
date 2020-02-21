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
import com.ispong.oxygen.flysql.model.enums.SqlOperateType;
import com.ispong.oxygen.flysql.model.enums.SqlType;
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

    /**
     * add 'object'
     *
     * @param value object
     * @return 'object'
     * @since 0.0.1
     */
    private String addSingleQuote(Object value) {

        return "'" + value + "'";
    }

    /**
     * select('A','B')
     *
     * @param columnNames columnNames
     * @return self
     * @since 0.0.1
     */
    public T select(String... columnNames) {

        if (sqlType.equals(SqlType.VIEW_SELECT)) {
            sqlConditions.add(new SqlCondition(SqlOperateType.SELECT, "", Strings.join(Arrays.asList(columnNames), ',')));
        } else {
            List<String> columnNameList = new ArrayList<>(columnNames.length);
            for (String columnName : columnNames) {
                columnNameList.add(columnsMap.get(columnName) + " " + columnName);
            }
            sqlConditions.add(new SqlCondition(SqlOperateType.SELECT, "", Strings.join(columnNameList, ',')));
        }
        return getSelf();
    }

    /**
     * setVar()
     *
     * @param columnName columnName
     * @param value      value
     * @return self
     * @since 0.0.1
     */
    public T setValue(String columnName, String value) {

        sqlConditions.add(new SqlCondition(SqlOperateType.SET_VALUE, ":" + columnName, "'" + value + "'"));
        return getSelf();
    }

    /**
     * or()
     *
     * @return self
     * @since 0.0.1
     */
    public T or() {

        sqlConditions.add(new SqlCondition(SqlOperateType.OR, "", ""));
        return getSelf();
    }

    /**
     * and()
     *
     * @return self
     * @since 0.0.1
     */
    public T and() {

        sqlConditions.add(new SqlCondition(SqlOperateType.AND, "", ""));
        return getSelf();
    }

    /**
     * eq()
     *
     * @param columnName columnName
     * @param value      value
     * @return self
     * @since 0.0.1
     */
    public T eq(String columnName, Object value) {

        sqlConditions.add(new SqlCondition(SqlOperateType.EQ, columnsMap.get(columnName), addSingleQuote(value)));
        return getSelf();
    }

    /**
     * ne()
     *
     * @param columnName columnName
     * @param value      value
     * @return self
     * @since 0.0.1
     */
    public T ne(String columnName, Object value) {

        sqlConditions.add(new SqlCondition(SqlOperateType.NE, columnsMap.get(columnName), addSingleQuote(value)));
        return getSelf();
    }

    /**
     * gt()
     *
     * @param columnName columnName
     * @param value      value
     * @return self
     * @since 0.0.1
     */
    public T gt(String columnName, Object value) {

        sqlConditions.add(new SqlCondition(SqlOperateType.GT, columnsMap.get(columnName), addSingleQuote(value)));
        return getSelf();
    }

    /**
     * gtEq()
     *
     * @param columnName columnName
     * @param value      value
     * @return self
     * @since 0.0.1
     */
    public T gtEq(String columnName, Object value) {

        sqlConditions.add(new SqlCondition(SqlOperateType.GT_EQ, columnsMap.get(columnName), addSingleQuote(value)));
        return getSelf();
    }

    /**
     * lt()
     *
     * @param columnName columnName
     * @param value      value
     * @return self
     * @since 0.0.1
     */
    public T lt(String columnName, Object value) {

        sqlConditions.add(new SqlCondition(SqlOperateType.LT, columnsMap.get(columnName), addSingleQuote(value)));
        return getSelf();
    }

    /**
     * ltEq()
     *
     * @param columnName columnName
     * @param value      value
     * @return self
     * @since 0.0.1
     */
    public T ltEq(String columnName, Object value) {

        sqlConditions.add(new SqlCondition(SqlOperateType.LT_EQ, columnsMap.get(columnName), addSingleQuote(value)));
        return getSelf();
    }

    /**
     * in()
     *
     * @param columnName columnName
     * @param values     values
     * @return self
     * @since 0.0.1
     */
    public T in(String columnName, Object... values) {

        List<String> inValues = new ArrayList<>();
        Arrays.stream(values).forEach(v -> inValues.add(addSingleQuote(v)));
        sqlConditions.add(new SqlCondition(SqlOperateType.IN, columnsMap.get(columnName), "(" + Strings.join(inValues, ',') + ")"));
        return getSelf();
    }

    /**
     * notIn()
     *
     * @param columnName columnName
     * @param values     values
     * @return self
     * @since 0.0.1
     */
    public T notIn(String columnName, Object... values) {

        List<String> inValues = new ArrayList<>();
        Arrays.stream(values).forEach(v -> inValues.add(addSingleQuote(v)));
        sqlConditions.add(new SqlCondition(SqlOperateType.NOT_IN, columnsMap.get(columnName), "(" + Strings.join(inValues, ',') + ")"));
        return getSelf();
    }

    /**
     * between()
     *
     * @param columnName columnName
     * @param value1     value1
     * @param value2     value2
     * @return self
     * @since 0.0.1
     */
    public T between(String columnName, Object value1, Object value2) {

        sqlConditions.add(new SqlCondition(SqlOperateType.BETWEEN, columnsMap.get(columnName), "(" + addSingleQuote(value1) + " and " + addSingleQuote(value2) + ")"));
        return getSelf();
    }

    /**
     * between()
     *
     * @param columnName columnName
     * @param value1     value1
     * @param value2     value2
     * @return self
     * @since 0.0.1
     */
    public T notBetween(String columnName, Object value1, Object value2) {

        sqlConditions.add(new SqlCondition(SqlOperateType.NOT_BETWEEN, columnsMap.get(columnName), "(" + addSingleQuote(value1) + " and " + addSingleQuote(value2) + ")"));
        return getSelf();
    }

    /**
     * orderBy()
     *
     * @param columnName columnName
     * @param orderType  orderType(desc,asc)
     * @return self
     * @since 0.0.1
     */
    public T orderBy(String columnName, String orderType) {

        sqlConditions.add(new SqlCondition(SqlOperateType.ORDER_BY, columnsMap.get(columnName), orderType));
        return getSelf();
    }

    /**
     * like()
     *
     * @param columnName columnName
     * @param value      value
     * @return self
     * @since 0.0.1
     */
    public T like(String columnName, String value) {

        sqlConditions.add(new SqlCondition(SqlOperateType.LIKE, columnsMap.get(columnName), addSingleQuote("%" + value + "%")));
        return getSelf();
    }

    /**
     * notLike()
     *
     * @param columnName columnName
     * @param value      value
     * @return self
     * @since 0.0.1
     */
    public T notLike(String columnName, String value) {

        sqlConditions.add(new SqlCondition(SqlOperateType.NOT_LIKE, columnsMap.get(columnName), addSingleQuote("%" + value + "%")));
        return getSelf();
    }

    /**
     * limit()
     *
     * @param value limit number
     * @return self
     * @since 0.0.1
     */
    public T limit(Integer value) {

        sqlConditions.add(new SqlCondition(SqlOperateType.LIMIT, "", value));
        return getSelf();
    }

    /**
     * update()
     *
     * @param columnName columnName
     * @param value      value
     * @return self
     * @since 0.0.1
     */
    public T update(String columnName, Object value) {

        sqlConditions.add(new SqlCondition(SqlOperateType.UPDATE, columnsMap.get(columnName), addSingleQuote(value)));
        return getSelf();
    }

    /**
     * isNull()
     *
     * @param columnName columnName
     * @return self
     * @since 0.0.1
     */
    public T isNull(String columnName) {

        sqlConditions.add(new SqlCondition(SqlOperateType.IS_NULL, columnsMap.get(columnName), ""));
        return getSelf();
    }

    /**
     * isNull()
     *
     * @param columnName columnName
     * @return self
     * @since 0.0.1
     */
    public T isNotNull(String columnName) {

        sqlConditions.add(new SqlCondition(SqlOperateType.IS_NOT_NULL, columnsMap.get(columnName), ""));
        return getSelf();
    }

    public T sql() {

        return getSelf();
    }

    public T unSelect() {

        return getSelf();
    }

    public T distinct() {

        return getSelf();
    }

    public T groupBy() {

        return getSelf();
    }

    public T having(String... conditions) {

        return getSelf();
    }
}
