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

import com.ispong.oxygen.flysql.annotation.DateBaseType;
import com.ispong.oxygen.flysql.annotation.FlysqlView;
import com.ispong.oxygen.flysql.annotation.FlysqlViews;
import com.ispong.oxygen.flysql.annotation.TableName;
import com.ispong.oxygen.flysql.model.SqlCondition;
import com.ispong.oxygen.flysql.model.SqlOperateType;
import com.ispong.oxygen.flysql.model.SqlType;
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
 * sql builder
 *
 * @author ispong
 * @version v0.1.0
 */
@Slf4j
public class SqlExecutor<A> extends AbstractSqlBuilder<SqlExecutor<A>> implements SqlOperations{

    @Getter
    private Class<A> genericType;

    private static JdbcTemplate jdbcTemplate;

    private SqlType sqlType;

    private Map<String, String> columnsMap;

    public SqlExecutor(Class<A> genericType, JdbcTemplate jdbcTemplate, SqlType sqlType, Map<String, String> columnsMap) {

        super(sqlType, columnsMap);
        this.columnsMap = columnsMap;
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
     *
     *
     * @param
     * @return
     * @since 0.0.1
     */
    public Boolean hasOperateType(SqlCondition sqlCondition,SqlOperateType sqlOperateType) {

        if (sqlCondition == null) {
            return true;
        }
        return sqlCondition.getOperateType().equals(sqlOperateType);
    }

    public void addWhere(SqlCondition sqlConditionTemp, StringBuilder sqlStringBuilder) {

        if (hasOperateType(sqlConditionTemp, SqlOperateType.SELECT) || hasOperateType(sqlConditionTemp, SqlOperateType.SET_VALUE)) {
            sqlStringBuilder.append(" where ");
        } else {
            sqlStringBuilder.append(" and ");
        }
    }

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
                case BETWEEN:
                    addWhere(sqlConditionTemp, sqlStringBuilder);
                    sqlStringBuilder.append(sqlConditionMeta.getColumnName()).append(" between ").append(sqlConditionMeta.getValue());
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
        if (sqlType.equals(SqlType.SELECT) && selectFlag) {
            List<String> columnsList = new ArrayList<>();
            columnsMap.forEach((k, v) -> columnsList.add(v + " " + k));
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
            FlysqlView[] flysqlViews = getGenericType().getAnnotation(FlysqlViews.class).value();
            for (FlysqlView flysqlViewMeta : flysqlViews) {
                if (flysqlViewMeta.type().equals(DateBaseType.MYSQL)) {
                    return parseSqlConditions(" select * from (" + flysqlViews[0].value() + ") temp ", sqlConditions);
                }
            }
        }
        return parseSqlConditions("select * from " + getTableName(), sqlConditions);
    }

    public String parseDeleteSql(){

        return parseSqlConditions("delete from " + getTableName(), sqlConditions);
    }

    public String parseUpdateSql() {
        StringBuilder sqlStringBuilder = new StringBuilder("update " + getTableName() + " set ");
        for (SqlCondition sqlConditionMeta : sqlConditions) {
            if (sqlConditionMeta.getOperateType().equals(SqlOperateType.UPDATE)) {
                sqlStringBuilder.append(sqlConditionMeta.getColumnName()).append(" = ").append(sqlConditionMeta.getValue());
            }
        }
        return parseSqlConditions(sqlStringBuilder.toString(), sqlConditions);
    }

    @Override
    @SuppressWarnings("unchecked")
    public A getOne() {

        String sqlString = parseSelectSql();
        log.debug("[sql]:" + sqlString);
        return jdbcTemplate.queryForObject(sqlString, new BeanPropertyRowMapper<>(getGenericType()));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<A> query() {

        String sqlString = parseSelectSql();
        log.debug("[sql]:" + sqlString);
        return jdbcTemplate.query(sqlString, new BeanPropertyRowMapper<>(getGenericType()));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<A> query(Integer page, Integer size) {

        String sqlString = parseSelectSql() + " limit " + (page - 1) * size + " , " + size;
        log.debug("[sql]:" + sqlString);
        return jdbcTemplate.query(sqlString, new BeanPropertyRowMapper<>(getGenericType()));
    }

    @Override
    public void doUpdate() {
        String sqlString = parseUpdateSql();
        log.debug("[sql]:" + sqlString);
        jdbcTemplate.update(sqlString);
    }

    @Override
    public <A> void save(A obj) throws InvocationTargetException, IllegalAccessException {

        PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(genericType);
        List<String> valueList = new ArrayList<>();
        List<String> columnList = new ArrayList<>();
        for (PropertyDescriptor propertyMeta : propertyDescriptors) {
            if(propertyMeta.getName().equals("class")){
                continue;
            }
            Object invoke = propertyMeta.getReadMethod().invoke(obj);
            valueList.add("'" + invoke + "'");
            columnList.add(columnsMap.get(propertyMeta.getName()));
        }
        columnsMap.forEach((k,v)-> columnList.add(v));
        String sqlString = "insert into " + getTableName() + "(" + Strings.join(columnList, ',') + ") value (" + Strings.join(valueList, ',') + ")";
        log.debug("[sql]:" + sqlString);
        jdbcTemplate.execute(sqlString);
    }

    @Override
    public void doDelete() {

        String sqlString = parseDeleteSql();
        log.debug("[sql]:" + sqlString);
        jdbcTemplate.update(sqlString);
    }
}
