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
package com.ispong.oxygen.flysql.core;

import com.ispong.oxygen.flysql.annotation.FlysqlView;
import com.ispong.oxygen.flysql.annotation.FlysqlViews;
import com.ispong.oxygen.flysql.exception.FlysqlException;
import com.ispong.oxygen.flysql.pojo.constant.FlysqlConstants;
import com.ispong.oxygen.flysql.pojo.constant.JavaTypeConstants;
import com.ispong.oxygen.flysql.pojo.entity.FlysqlKey;
import com.ispong.oxygen.flysql.pojo.entity.SqlCondition;
import com.ispong.oxygen.flysql.pojo.enums.SqlOperateType;
import com.ispong.oxygen.flysql.pojo.enums.SqlType;
import com.ispong.oxygen.flysql.utils.FlysqlUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * flysql 核心构造中心
 *
 * @author ispong
 * @version v0.1.0
 */
@Slf4j
public class FlysqlBuilder<A> extends AbstractSqlBuilder<FlysqlBuilder<A>> implements FlysqlExecutor<A> {

    private final FlysqlKey<A> flysqlKey;

    public FlysqlBuilder(FlysqlKey<A> flysqlKey) {

        // 为了获取父级的属性值
        super(flysqlKey.getTargetClass());
        this.flysqlKey = flysqlKey;
    }

    @Override
    public FlysqlBuilder<A> getSelf() {
        return this;
    }

    /**
     * 解析flysql的条件
     *
     * @param sqlString     初始化sql
     * @param sqlConditions sql的拼接条件
     * @return sqlString
     * @since 0.0.1
     */
    public String parseSqlConditions(String sqlString, List<SqlCondition> sqlConditions) {

        // 转化StringBuilder拼接
        StringBuilder sqlStringBuilder = new StringBuilder(sqlString);

        // 判断标识是否使用了select做自定义查询
        boolean selectFlag = true;

        // 前一个拼接条件临时储存
        SqlCondition sqlConditionTemp = null;

        // 遍历所有的拼接条件
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

    /**
     * 初始化select查询的sql
     *
     * @return sqlString
     * @since 0.0.1
     */
    public String initSelectSql() {

        if (flysqlKey.getSqlType().equals(SqlType.VIEW)) {

            // 多视图初始化
            if (flysqlKey.getTargetClass().isAnnotationPresent(FlysqlViews.class)) {
                FlysqlView[] flysqlViews = flysqlKey.getTargetClass().getAnnotation(FlysqlViews.class).value();
                for (FlysqlView metaFlysqlView : flysqlViews) {
                    if (flysqlKey.getViewSqlName().equals(metaFlysqlView.name())) {
                        return " select " + FlysqlConstants.SELECT_REPLACE_CONTENT + " from ( " + metaFlysqlView.value() + " ) flysql ";
                    }
                }
            }

            // 单视图初始化
            if (flysqlKey.getTargetClass().isAnnotationPresent(FlysqlView.class)) {
                FlysqlView flysqlView = flysqlKey.getTargetClass().getAnnotation(FlysqlView.class);
                return " select " + FlysqlConstants.SELECT_REPLACE_CONTENT + " from ( " + flysqlView.value() + " ) flysql ";
            }

            throw new FlysqlException("view is not exist");
        } else {

            String tableName = FlysqlUtils.getTableName(flysqlKey.getTargetClass());
            return tableName == null ? "" : "select " + FlysqlConstants.SELECT_REPLACE_CONTENT + " from " + tableName;
        }

    }

    /**
     * 初始化save插入的sql
     *
     * @param entity 对象实体
     * @return sqlString
     * @since 0.0.1
     */
    public String initSaveSql(Object entity) {

        PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(flysqlKey.getTargetClass());

        List<String> columnList = new ArrayList<>();
        List<String> valueList = new ArrayList<>();

        // 解析所有属性和它对应的值
        for (PropertyDescriptor propertyMeta : propertyDescriptors) {
            if (!FlysqlConstants.CLASS.equals(propertyMeta.getName())) {

                Object invoke;
                try {
                    invoke = propertyMeta.getReadMethod().invoke(entity);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    continue;
                }

                columnList.add(columnsMap.get(propertyMeta.getName()));
                if (propertyMeta.getPropertyType().getName().equals(JavaTypeConstants.Boolean)) {
                    valueList.add(invoke == null ? "''" : invoke.toString());
                } else {
                    valueList.add(invoke == null ? "''" : FlysqlUtils.addSingleQuote(invoke));
                }
            }
        }
        return "insert into " + FlysqlUtils.getTableName(flysqlKey.getTargetClass()) + " ( " + Strings.join(columnList, ',') + " ) values ( " + Strings.join(valueList, ',') + ")";
    }

    // ---------------------------------------- 执行jdbcTemplate操作 ----------------------------------------

    @Override
    public A getOne() {

        String sqlString = parseSqlConditions(initSelectSql(), sqlConditions);
        log.debug("[oxygen-flysql-sql]:" + sqlString);

        try {
            return flysqlKey.getJdbcTemplate().queryForObject(sqlString, new BeanPropertyRowMapper<>(flysqlKey.getTargetClass()));
        } catch (EmptyResultDataAccessException e) {
            if ("Incorrect result size: expected 1, actual 0".equals(e.getMessage())) {
                return null;
            }
            throw new FlysqlException(e.getMessage());
        }
    }

    @Override
    public List<A> query() {

        String sqlString = parseSqlConditions(initSelectSql(), sqlConditions);
        log.debug("[oxygen-flysql-sql]:" + sqlString);
        try {
            return flysqlKey.getJdbcTemplate().query(sqlString, new BeanPropertyRowMapper<>(flysqlKey.getTargetClass()));
        } catch (Exception e) {
            throw new FlysqlException(e.getMessage());
        }
    }

    @Override
    public List<A> query(Integer page, Integer size) {

        String sqlString = parseSqlConditions(initSelectSql(), sqlConditions) + " limit " + (page - 1) * size + " , " + size;
        log.debug("[oxygen-flysql-sql]:" + sqlString);
        try {
            return flysqlKey.getJdbcTemplate().query(sqlString, new BeanPropertyRowMapper<>(flysqlKey.getTargetClass()));
        } catch (Exception e) {
            throw new FlysqlException(e.getMessage());
        }
    }

    @Override
    public void doUpdate() {

        StringBuilder sqlStringBuilder = new StringBuilder("update " + FlysqlUtils.getTableName(flysqlKey.getTargetClass()) + " set ");
        for (SqlCondition sqlConditionMeta : sqlConditions) {
            if (sqlConditionMeta.getOperateType().equals(SqlOperateType.UPDATE)) {
                sqlStringBuilder.append(sqlConditionMeta.getColumnName()).append(" = ").append(sqlConditionMeta.getValue());
            }
        }
        String sqlString = parseSqlConditions(sqlStringBuilder.toString(), sqlConditions);
        log.debug("[oxygen-flysql-sql]" + sqlString);
        try {
            flysqlKey.getJdbcTemplate().update(sqlString);
        } catch (Exception e) {
            throw new FlysqlException(e.getMessage());
        }
    }

    @Override
    public void save(Object entity) {

        String sqlString = initSaveSql(entity);
        log.debug("[oxygen-flysql-sql]:" + sqlString);
        try {
            flysqlKey.getJdbcTemplate().execute(sqlString);
        } catch (Exception e) {
            throw new FlysqlException(e.getMessage());
        }
    }

    @Override
    public void doDelete() {

        String sqlString = parseSqlConditions("delete from " + FlysqlUtils.getTableName(flysqlKey.getTargetClass()), sqlConditions);
        log.debug("[oxygen-flysql-sql]:" + sqlString);
        try {
            flysqlKey.getJdbcTemplate().update(sqlString);
        }catch (Exception e) {
            throw new FlysqlException(e.getMessage());
        }
    }

    @Override
    public Integer count() {

        String sqlString = parseSqlConditions("select count(1) from " + FlysqlUtils.getTableName(flysqlKey.getTargetClass()), sqlConditions);
        log.debug("[oxygen-flysql-sql]:" + sqlString);
        try {
            return flysqlKey.getJdbcTemplate().queryForObject(sqlString, Integer.class);
        } catch (Exception e) {
            throw new FlysqlException(e.getMessage());
        }
    }
}
