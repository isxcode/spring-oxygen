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

import com.ispong.oxygen.flysql.annotation.FlysqlView;
import com.ispong.oxygen.flysql.annotation.TableName;
import com.ispong.oxygen.flysql.model.SqlCondition;
import com.ispong.oxygen.flysql.model.enums.DateBaseType;
import com.ispong.oxygen.flysql.model.enums.SqlOperateType;
import com.ispong.oxygen.flysql.model.enums.SqlType;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * sql executor
 *
 * @author ispong
 * @version v0.1.0
 */
@Slf4j
public class SqlExecutor<A> extends AbstractSqlBuilder<SqlExecutor<A>> implements SqlOperations {

    @Getter
    private Class<A> genericType;

    private static JdbcTemplate jdbcTemplate;

    private SqlType sqlType;

    private Map<String, String> columnNameMap;

    public SqlExecutor(Class<A> genericType, JdbcTemplate jdbcTemplate, SqlType sqlType, Map<String, String> columnNameMap) {

        super(sqlType, columnNameMap);
        this.columnNameMap = columnNameMap;
        this.sqlType = sqlType;
        this.genericType = genericType;
        SqlExecutor.jdbcTemplate = jdbcTemplate;

    }

    @Override
    public SqlExecutor<A> getSelf() {

        return this;
    }

    /**
     * get table name
     *
     * @return tableName
     * @since 0.0.1
     */
    public String getTableName() {
        return getGenericType().getAnnotation(TableName.class).value();
    }

    /**
     * 是否存在操作条件
     *
     * @param sqlCondition   sql条件
     * @param sqlOperateType sql操作类型
     * @return true存在
     * @since 0.0.1
     */
    public Boolean hasOperateType(SqlCondition sqlCondition, SqlOperateType sqlOperateType) {

        if (sqlCondition == null) {
            return true;
        }
        return sqlCondition.getOperateType().equals(sqlOperateType);
    }

    /**
     * add where
     *
     * @param sqlConditionTemp sql条件
     * @param sqlStringBuilder sql语句
     * @since 0.0.1
     */
    public void addWhere(SqlCondition sqlConditionTemp, StringBuilder sqlStringBuilder) {

        // 如果前面存在select或者setVar说明一定没有where,所以添加where
        if (hasOperateType(sqlConditionTemp, SqlOperateType.SELECT) || hasOperateType(sqlConditionTemp, SqlOperateType.SET_VALUE)) {
            sqlStringBuilder.append(" where ");
        } else {
            sqlStringBuilder.append(" and ");
        }
    }

    /**
     * add order by
     *
     * @param sqlConditionTemp sql条件
     * @param sqlStringBuilder sql语句
     * @since 0.0.1
     */
    public void addOrderBy(SqlCondition sqlConditionTemp, StringBuilder sqlStringBuilder) {

        if (hasOperateType(sqlConditionTemp, SqlOperateType.ORDER_BY)) {
            sqlStringBuilder.append(",");
        } else {
            sqlStringBuilder.append(" order by ");
        }
    }

    /**
     * parse sql conditions
     *
     * @param sqlString     init sql
     * @param sqlConditions sqlConditions
     * @return sqlString
     * @since 0.0.1
     */
    public String parseSqlConditions(String sqlString, List<SqlCondition> sqlConditions) {

        StringBuilder sqlStringBuilder = new StringBuilder(sqlString);
        SqlCondition sqlConditionTemp = null;
        boolean selectFlag = true;
        for (SqlCondition sqlConditionMeta : sqlConditions) {
            switch (sqlConditionMeta.getOperateType()) {
                case SELECT:
                    sqlStringBuilder = new StringBuilder(sqlStringBuilder.toString().replace("*", String.valueOf(sqlConditionMeta.getValue())));
                    selectFlag = false;
                    break;
                case SET_VALUE:
                    sqlStringBuilder = new StringBuilder(sqlStringBuilder.toString().replace(sqlConditionMeta.getColumnName(), String.valueOf(sqlConditionMeta.getValue())));
                    break;
                case OR:
                    sqlStringBuilder.append(" or ");
                    break;
                case AND:
                    sqlStringBuilder.append(" and ");
                    break;
                case LT:
                    addWhere(sqlConditionTemp, sqlStringBuilder);
                    sqlStringBuilder.append(sqlConditionMeta.getColumnName()).append(" < ").append(sqlConditionMeta.getValue());
                    break;
                case LT_EQ:
                    addWhere(sqlConditionTemp, sqlStringBuilder);
                    sqlStringBuilder.append(sqlConditionMeta.getColumnName()).append(" <= ").append(sqlConditionMeta.getValue());
                    break;
                case EQ:
                    addWhere(sqlConditionTemp, sqlStringBuilder);
                    sqlStringBuilder.append(sqlConditionMeta.getColumnName()).append(" = ").append(sqlConditionMeta.getValue());
                    break;
                case NE:
                    addWhere(sqlConditionTemp, sqlStringBuilder);
                    sqlStringBuilder.append(sqlConditionMeta.getColumnName()).append(" != ").append(sqlConditionMeta.getValue());
                    break;
                case GT:
                    addWhere(sqlConditionTemp, sqlStringBuilder);
                    sqlStringBuilder.append(sqlConditionMeta.getColumnName()).append(" > ").append(sqlConditionMeta.getValue());
                    break;
                case GT_EQ:
                    addWhere(sqlConditionTemp, sqlStringBuilder);
                    sqlStringBuilder.append(sqlConditionMeta.getColumnName()).append(" >= ").append(sqlConditionMeta.getValue());
                    break;
                case IN:
                    addWhere(sqlConditionTemp, sqlStringBuilder);
                    sqlStringBuilder.append(sqlConditionMeta.getColumnName()).append(" in ").append(sqlConditionMeta.getValue());
                    break;
                case NOT_IN:
                    addWhere(sqlConditionTemp, sqlStringBuilder);
                    sqlStringBuilder.append(sqlConditionMeta.getColumnName()).append(" not in ").append(sqlConditionMeta.getValue());
                    break;
                case LIKE:
                    addWhere(sqlConditionTemp, sqlStringBuilder);
                    sqlStringBuilder.append(sqlConditionMeta.getColumnName()).append(" like ").append(sqlConditionMeta.getValue());
                    break;
                case NOT_LIKE:
                    addWhere(sqlConditionTemp, sqlStringBuilder);
                    sqlStringBuilder.append(sqlConditionMeta.getColumnName()).append(" not like ").append(sqlConditionMeta.getValue());
                    break;
                case IS_NULL:
                    addWhere(sqlConditionTemp, sqlStringBuilder);
                    sqlStringBuilder.append(sqlConditionMeta.getColumnName()).append(" is null ");
                    break;
                case IS_NOT_NULL:
                    addWhere(sqlConditionTemp, sqlStringBuilder);
                    sqlStringBuilder.append(sqlConditionMeta.getColumnName()).append(" is not null ");
                    break;
                case BETWEEN:
                    addWhere(sqlConditionTemp, sqlStringBuilder);
                    sqlStringBuilder.append(sqlConditionMeta.getColumnName()).append(" between ").append(sqlConditionMeta.getValue());
                    break;
                case NOT_BETWEEN:
                    addWhere(sqlConditionTemp, sqlStringBuilder);
                    sqlStringBuilder.append(sqlConditionMeta.getColumnName()).append(" not between ").append(sqlConditionMeta.getValue());
                    break;
                case ORDER_BY:
                    addOrderBy(sqlConditionTemp, sqlStringBuilder);
                    sqlStringBuilder.append(sqlConditionMeta.getColumnName()).append(" ").append(sqlConditionMeta.getValue());
                    break;
                case LIMIT:
                    sqlStringBuilder.append(" limit ").append(sqlConditionMeta.getValue());
                    break;
                default:
            }
            sqlConditionTemp = sqlConditionMeta;
        }
        // 如果没有执行select,则把* 替换成对象属性
        if (sqlType.equals(SqlType.SELECT) && selectFlag) {

            List<String> columnsList = new ArrayList<>();
            columnNameMap.forEach((k, v) -> columnsList.add(v + " " + k));
            return sqlStringBuilder.toString().replace("*", Strings.join(columnsList, ','));
        }
        return sqlStringBuilder.toString();
    }

    /**
     * parse select or view_select
     *
     * @return sqlString
     * @since 0.0.1
     */
    public String parseSelectSql() {

        if (sqlType.equals(SqlType.VIEW_SELECT)) {
            FlysqlView flysqlView = getGenericType().getAnnotation(FlysqlView.class);
            if (flysqlView.type().equals(DateBaseType.MYSQL)) {
                return parseSqlConditions(" select * from (" + flysqlView.value() + ") flysql ", sqlConditions);
            }
        }
        return parseSqlConditions("select * from " + getTableName(), sqlConditions);
    }

    /**
     * parse delete
     *
     * @return sqlString
     * @since 0.0.1
     */
    public String parseDeleteSql() {

        return parseSqlConditions("delete from " + getTableName(), sqlConditions);
    }

    /**
     * parse update
     *
     * @return sqlString
     * @since 0.0.1
     */
    public String parseUpdateSql() {

        StringBuilder sqlStringBuilder = new StringBuilder("update " + getTableName() + " set ");
        for (SqlCondition sqlConditionMeta : sqlConditions) {
            if (sqlConditionMeta.getOperateType().equals(SqlOperateType.UPDATE)) {
                sqlStringBuilder.append(sqlConditionMeta.getColumnName()).append(" = ").append(sqlConditionMeta.getValue());
            }
        }
        return parseSqlConditions(sqlStringBuilder.toString(), sqlConditions);
    }

    /**
     * getOne
     *
     * @return data
     * @since 0.0.1
     */
    @Override
    public A getOne() {

        String sqlString = parseSelectSql();
        log.debug("[sql]:" + sqlString);
        return jdbcTemplate.queryForObject(sqlString, new BeanPropertyRowMapper<>(getGenericType()));
    }

    /**
     * query
     *
     * @return List[Data]
     * @since 0.0.1
     */
    @Override
    public List<A> query() {

        String sqlString = parseSelectSql();
        log.debug("[sql]:" + sqlString);
        return jdbcTemplate.query(sqlString, new BeanPropertyRowMapper<>(getGenericType()));
    }

    /**
     * query page size
     *
     * @param page page
     * @param size size
     * @return List[Data]
     * @since 0.0.1
     */
    @Override
    public List<A> query(Integer page, Integer size) {

        String sqlString = parseSelectSql() + " limit " + (page - 1) * size + " , " + size;
        log.debug("[sql]:" + sqlString);
        return jdbcTemplate.query(sqlString, new BeanPropertyRowMapper<>(getGenericType()));
    }

    /**
     * doUpdate()
     *
     * @since 0.0.1
     */
    @Override
    public void doUpdate() {

        String sqlString = parseUpdateSql();
        log.debug("[sql]:" + sqlString);
        jdbcTemplate.update(sqlString);
    }

    /**
     * doDelete()
     *
     * @since 0.0.1
     */
    @Override
    public void doDelete() {

        String sqlString = parseDeleteSql();
        log.debug("[sql]:" + sqlString);
        jdbcTemplate.update(sqlString);
    }

    @Override
    public Integer count() {

        String sqlString = parseSqlConditions("select count(1) from " + getTableName(), sqlConditions);
        log.debug("[sql]:" + sqlString);
        return jdbcTemplate.queryForObject(sqlString, Integer.class);
    }

    /**
     * save()
     *
     * @param obj data
     * @since 0.0.1
     */
    @Override
    public void save(Object obj) {

        PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(genericType);
        List<String> valueList = new ArrayList<>();
        List<String> columnList = new ArrayList<>();
        for (PropertyDescriptor propertyMeta : propertyDescriptors) {
            if (FlysqlConstants.CLASS.equals(propertyMeta.getName())) {
                continue;
            }
            try {
                Object value = propertyMeta.getReadMethod().invoke(obj);
                if (value == null) {
                    continue;
                }
                valueList.add("'" + value + "'");
                columnList.add(columnNameMap.get(propertyMeta.getName()));
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        String sqlString = "insert into " + getTableName() + "(" + Strings.join(columnList, ',') + ") value (" + Strings.join(valueList, ',') + ")";
        log.debug("[sql]:" + sqlString);
        jdbcTemplate.execute(sqlString);
    }

}
