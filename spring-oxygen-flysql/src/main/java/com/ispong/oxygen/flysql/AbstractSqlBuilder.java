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

import com.ispong.oxygen.flysql.enums.SqlOperateType;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 条件组装器
 *
 * @author ispong
 * @version v0.1.0
 */
public abstract class AbstractSqlBuilder<T> implements FlysqlCondition<T> {

    public final List<SqlCondition> sqlConditions = new ArrayList<>();

    public final Map<String, String> columnsMap;

    public AbstractSqlBuilder(Class<?> genericType) {
        this.columnsMap = FlysqlUtils.parseBeanName(genericType);
    }

    /**
     * 获取自己
     *
     * @return self[T]
     * @since 0.0.1
     */
    public abstract T getSelf();

    @Override
    public T select(String... columnNames) {

        List<String> columnNameList = new ArrayList<>(columnNames.length);
        for (String columnName : columnNames) {
            columnNameList.add(columnsMap.get(columnName) + " " + columnName);
        }
        sqlConditions.add(new SqlCondition(SqlOperateType.SELECT, "", Strings.join(columnNameList, ',')));
        return getSelf();
    }

    @Override
    public T unSelect(String... columnNames) {

        return getSelf();
    }

    @Override
    public T setValue(String columnName, String value) {

        sqlConditions.add(new SqlCondition(SqlOperateType.SET_VALUE, ":" + columnName, FlysqlUtils.addSingleQuote(value)));
        return getSelf();
    }

    @Override
    public T or() {

        sqlConditions.add(new SqlCondition(SqlOperateType.OR, "", ""));
        return getSelf();
    }

    @Override
    public T and() {

        sqlConditions.add(new SqlCondition(SqlOperateType.AND, "", ""));
        return getSelf();
    }

    @Override
    public T eq(String columnName, Object value) {

        sqlConditions.add(new SqlCondition(SqlOperateType.EQ, columnsMap.get(columnName), FlysqlUtils.addSingleQuote(value)));
        return getSelf();
    }

    @Override
    public T ne(String columnName, Object value) {

        sqlConditions.add(new SqlCondition(SqlOperateType.NE, columnsMap.get(columnName), FlysqlUtils.addSingleQuote(value)));
        return getSelf();
    }

    @Override
    public T gt(String columnName, Object value) {

        sqlConditions.add(new SqlCondition(SqlOperateType.GT, columnsMap.get(columnName), FlysqlUtils.addSingleQuote(value)));
        return getSelf();
    }

    @Override
    public T gtEq(String columnName, Object value) {

        sqlConditions.add(new SqlCondition(SqlOperateType.GT_EQ, columnsMap.get(columnName), FlysqlUtils.addSingleQuote(value)));
        return getSelf();
    }

    @Override
    public T lt(String columnName, Object value) {

        sqlConditions.add(new SqlCondition(SqlOperateType.LT, columnsMap.get(columnName), FlysqlUtils.addSingleQuote(value)));
        return getSelf();
    }

    @Override
    public T ltEq(String columnName, Object value) {

        sqlConditions.add(new SqlCondition(SqlOperateType.LT_EQ, columnsMap.get(columnName), FlysqlUtils.addSingleQuote(value)));
        return getSelf();
    }

    @Override
    public T in(String columnName, Object... values) {

        List<String> inValues = new ArrayList<>();
        Arrays.stream(values).forEach(v -> inValues.add(FlysqlUtils.addSingleQuote(v)));
        sqlConditions.add(new SqlCondition(SqlOperateType.IN, columnsMap.get(columnName), "(" + Strings.join(inValues, ',') + ")"));
        return getSelf();
    }

    @Override
    public T notIn(String columnName, Object... values) {

        List<String> inValues = new ArrayList<>();
        Arrays.stream(values).forEach(v -> inValues.add(FlysqlUtils.addSingleQuote(v)));
        sqlConditions.add(new SqlCondition(SqlOperateType.NOT_IN, columnsMap.get(columnName), "(" + Strings.join(inValues, ',') + ")"));
        return getSelf();
    }

    @Override
    public T between(String columnName, Object value1, Object value2) {

        sqlConditions.add(new SqlCondition(SqlOperateType.BETWEEN, columnsMap.get(columnName), "(" + FlysqlUtils.addSingleQuote(value1) + " and " + FlysqlUtils.addSingleQuote(value2) + ")"));
        return getSelf();
    }

    @Override
    public T notBetween(String columnName, Object value1, Object value2) {

        sqlConditions.add(new SqlCondition(SqlOperateType.NOT_BETWEEN, columnsMap.get(columnName), "(" + FlysqlUtils.addSingleQuote(value1) + " and " + FlysqlUtils.addSingleQuote(value2) + ")"));
        return getSelf();
    }

    @Override
    public T orderBy(String columnName, String orderType) {

        sqlConditions.add(new SqlCondition(SqlOperateType.ORDER_BY, columnsMap.get(columnName), orderType));
        return getSelf();
    }

    @Override
    public T like(String columnName, String value) {

        sqlConditions.add(new SqlCondition(SqlOperateType.LIKE, columnsMap.get(columnName), FlysqlUtils.addSingleQuote(("%" + value + "%"))));
        return getSelf();
    }

    @Override
    public T notLike(String columnName, String value) {

        sqlConditions.add(new SqlCondition(SqlOperateType.NOT_LIKE, columnsMap.get(columnName), FlysqlUtils.addSingleQuote(("%" + value + "%"))));
        return getSelf();
    }

    @Override
    public T limit(Integer value) {

        sqlConditions.add(new SqlCondition(SqlOperateType.LIMIT, "", value));
        return getSelf();
    }

    @Override
    public T update(String columnName, Object value) {

        sqlConditions.add(new SqlCondition(SqlOperateType.UPDATE, columnsMap.get(columnName), FlysqlUtils.addSingleQuote(value)));
        return getSelf();
    }

    @Override
    public T isNull(String columnName) {

        sqlConditions.add(new SqlCondition(SqlOperateType.IS_NULL, columnsMap.get(columnName), ""));
        return getSelf();
    }

    @Override
    public T isNotNull(String columnName) {

        sqlConditions.add(new SqlCondition(SqlOperateType.IS_NOT_NULL, columnsMap.get(columnName), ""));
        return getSelf();
    }
}
