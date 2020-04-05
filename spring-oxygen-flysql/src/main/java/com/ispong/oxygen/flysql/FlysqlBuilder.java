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

import com.ispong.oxygen.flysql.annotation.*;
import com.ispong.oxygen.flysql.enums.SqlOperateType;
import com.ispong.oxygen.flysql.enums.SqlType;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * sql 拼接对象
 *
 * @author ispong
 * @version v0.1.0
 */
@Slf4j
public class FlysqlBuilder<A> extends AbstractSqlBuilder<FlysqlBuilder<A>> implements FlysqlExecutor<A> {

    /**
     * 临时存储返回对象
     */
    @Getter
    private Class<A> genericType;

    /**
     * jdbcTemplate执行器
     */
    private JdbcTemplate jdbcTemplate;

    /**
     * 视图名称
     */
    private String viewSqlName;

    /**
     * sql类型
     */
    private SqlType sqlType;

    public FlysqlBuilder(SqlType sqlType, Class<A> genericType, JdbcTemplate jdbcTemplate, String viewSqlName) {

        super(genericType);
        this.sqlType = sqlType;
        this.genericType = genericType;
        this.jdbcTemplate = jdbcTemplate;
        this.viewSqlName = viewSqlName;
    }

    public FlysqlBuilder(SqlType sqlType, Class<A> genericType, JdbcTemplate jdbcTemplate) {

        super(genericType);
        this.sqlType = sqlType;
        this.genericType = genericType;
        this.jdbcTemplate = jdbcTemplate;
        this.viewSqlName = FlysqlConstants.PRIMARY_VIEW_NAME;
    }

    @Override
    public FlysqlBuilder<A> getSelf() {
        return this;
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
                    sqlStringBuilder = new StringBuilder(sqlStringBuilder.toString().replace(FlysqlConstants.SELECT_REPLACE_CONTENT, String.valueOf(sqlConditionMeta.getValue())));
                    selectFlag = false;
                    break;
                case SET_VALUE:
                    sqlStringBuilder = new StringBuilder(sqlStringBuilder.toString().replace(sqlConditionMeta.getColumnName(), String.valueOf(sqlConditionMeta.getValue())));
                    break;
                case OR:
                case AND:
                    sqlStringBuilder.append(sqlConditionMeta.getOperateType().getCode());
                    break;
                case ORDER_BY:
                    FlysqlUtils.addOrderBy(sqlConditionTemp, sqlStringBuilder);
                    sqlStringBuilder.append(sqlConditionMeta.getColumnName()).append(" ").append(sqlConditionMeta.getValue());
                    break;
                case UPDATE:
                    break;
                case SQL:
                    return sqlConditionMeta.getColumnName();
                default:
                    FlysqlUtils.addWhere(sqlConditionTemp, sqlStringBuilder);
                    sqlStringBuilder.append(sqlConditionMeta.getColumnName()).append(sqlConditionMeta.getOperateType().getCode()).append(sqlConditionMeta.getValue());
                    break;
            }
            sqlConditionTemp = sqlConditionMeta;
        }

        // 没有select条件,替换对象的所有属性
        if (selectFlag) {
            List<String> columnsList = new ArrayList<>();
            columnsMap.forEach((k, v) -> columnsList.add(v + " " + k));
            return sqlStringBuilder.toString().replace(FlysqlConstants.SELECT_REPLACE_CONTENT, Strings.join(columnsList, ','));
        }

        return sqlStringBuilder.toString();
    }

    public String initSelectSql() {

        if (sqlType.equals(SqlType.VIEW)) {
            if (getGenericType().isAnnotationPresent(FlysqlViews.class)) {
                FlysqlView[] flysqlViews = getGenericType().getAnnotation(FlysqlViews.class).value();
                for (FlysqlView metaFlysqlView : flysqlViews) {
                    if (viewSqlName.equals(metaFlysqlView.name())) {
                        return " select " + FlysqlConstants.SELECT_REPLACE_CONTENT + " from ( " + metaFlysqlView.value() + " ) flysql ";
                    }
                }
            }
            if (getGenericType().isAnnotationPresent(FlysqlView.class)) {
                FlysqlView flysqlView = getGenericType().getAnnotation(FlysqlView.class);
                return " select " + FlysqlConstants.SELECT_REPLACE_CONTENT + " from ( " + flysqlView.value() + " ) flysql ";
            }
            throw new FlysqlException("视图不存在");
        } else {
            String tableName = FlysqlUtils.getTableName(getGenericType());
            if (tableName == null) {
                return "";
            }
            return "select " + FlysqlConstants.SELECT_REPLACE_CONTENT + " from " + tableName;
        }
    }

    @Override
    public A getOne() {

        String sqlString = parseSqlConditions(initSelectSql(), sqlConditions);
        log.debug("[oxygen-flysql-sql]:" + sqlString);
        return jdbcTemplate.queryForObject(sqlString, new BeanPropertyRowMapper<>(getGenericType()));
    }

    @Override
    public List<A> query() {

        String sqlString = parseSqlConditions(initSelectSql(), sqlConditions);
        log.debug("[oxygen-flysql-sql]:" + sqlString);
        return jdbcTemplate.query(sqlString, new BeanPropertyRowMapper<>(getGenericType()));
    }

    @Override
    public List<A> query(Integer page, Integer size) {

        String sqlString = parseSqlConditions(initSelectSql(), sqlConditions) + " limit " + (page - 1) * size + " , " + size;
        log.debug("[oxygen-flysql-sql]:" + sqlString);
        return jdbcTemplate.query(sqlString, new BeanPropertyRowMapper<>(getGenericType()));
    }

    @Override
    public void doUpdate() {

        StringBuilder sqlStringBuilder = new StringBuilder("update " + FlysqlUtils.getTableName(getGenericType()) + " set ");
        for (SqlCondition sqlConditionMeta : sqlConditions) {
            if (sqlConditionMeta.getOperateType().equals(SqlOperateType.UPDATE)) {
                sqlStringBuilder.append(sqlConditionMeta.getColumnName()).append(" = ").append(sqlConditionMeta.getValue());
            }
        }
        String sqlString = parseSqlConditions(sqlStringBuilder.toString(), sqlConditions);
        log.debug("[oxygen-flysql-sql]" + sqlString);
        jdbcTemplate.update(sqlString);
    }

    @SneakyThrows
    @Override
    public void save(Object obj) {

        PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(genericType);

        List<String> valueList = new ArrayList<>();
        List<String> columnList = new ArrayList<>();

        for (PropertyDescriptor propertyMeta : propertyDescriptors) {
            if (!FlysqlConstants.CLASS.equals(propertyMeta.getName())) {
                String value;
                Field metaField = propertyMeta.getReadMethod().getDeclaringClass().getDeclaredField(propertyMeta.getName());
                if (metaField.isAnnotationPresent(CreatedBy.class) || metaField.isAnnotationPresent(LastModifiedBy.class)) {
                    value = "'" + SecurityContextHolder.getContext().getAuthentication().getName() + "'";
                } else if (metaField.isAnnotationPresent(CreatedDate.class) || metaField.isAnnotationPresent(LastModifiedDate.class)) {
                    value = "'" + LocalDateTime.now().toString() + "'";
                } else if (metaField.isAnnotationPresent(Version.class)) {
                    value = "'" + 1 + "'";
                } else {
                    try {
                        Object invoke = propertyMeta.getReadMethod().invoke(obj);
                        value = invoke == null ? "null" : "'" + invoke + "'";
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        value = "null";
                    }
                }
                columnList.add(columnsMap.get(propertyMeta.getName()));
                valueList.add(value);
            }
        }

        String sqlString = "insert into " + FlysqlUtils.getTableName(getGenericType()) + " ( " + Strings.join(columnList, ',') + " ) values ( " + Strings.join(valueList, ',') + ")";
        log.debug("[oxygen-flysql-sql]:" + sqlString);
        jdbcTemplate.execute(sqlString);
    }

    @Override
    public void doDelete() {

        String sqlString = parseSqlConditions("delete from " + FlysqlUtils.getTableName(getGenericType()), sqlConditions);
        log.debug("[oxygen-flysql-sql]:" + sqlString);
        jdbcTemplate.update(sqlString);
    }

    @Override
    public Integer count() {

        String sqlString = parseSqlConditions("select count(1) from " + FlysqlUtils.getTableName(getGenericType()), sqlConditions);
        log.debug("[oxygen-flysql-sql]:" + sqlString);
        return jdbcTemplate.queryForObject(sqlString, Integer.class);
    }

}
