package com.isxcode.oxygen.flysql.core;

import com.isxcode.oxygen.core.reflect.FieldBody;
import com.isxcode.oxygen.core.reflect.ReflectConstants;
import com.isxcode.oxygen.core.reflect.ReflectUtils;
import com.isxcode.oxygen.flysql.annotation.*;
import com.isxcode.oxygen.flysql.constant.FlysqlConstants;
import com.isxcode.oxygen.flysql.entity.FlysqlKey;
import com.isxcode.oxygen.flysql.entity.SqlCondition;
import com.isxcode.oxygen.flysql.enums.SqlOperateType;
import com.isxcode.oxygen.flysql.enums.SqlType;
import com.isxcode.oxygen.flysql.exception.FlysqlException;
import com.isxcode.oxygen.flysql.utils.FlysqlUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.security.core.context.SecurityContextHolder;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.isxcode.oxygen.flysql.enums.SqlOperateType.UPDATE;

/**
 * flysql 执行逻辑实现
 *
 * @author ispong
 * @version v0.1.0
 */
@Slf4j
public class FlysqlExecute<A> extends AbstractSqlBuilder<FlysqlExecute<A>> implements FlysqlExecutor<A> {

    /**
     * flysql 执行器的钥匙
     */
    private final FlysqlKey<A> flysqlKey;

    public FlysqlExecute(FlysqlKey<A> flysqlKey) {

        super(flysqlKey.getTargetClass(), flysqlKey.getDataBaseType());
        this.flysqlKey = flysqlKey;
    }

    @Override
    public FlysqlExecute<A> getSelf() {

        return this;
    }

    // ---------------------------------------- execute sql ----------------------------------------

    @Override
    public A getOne() {

        String sqlString = parseSqlConditions(initSelectSql(), sqlConditions);
        log.debug("[oxygen-flysql-sql]:" + sqlString);

        try {
            return flysqlKey.getJdbcTemplate().queryForObject(sqlString, new BeanPropertyRowMapper<>(flysqlKey.getTargetClass()));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<A> query() {

        try {
            if (flysqlKey.getJdbcTemplate() == null) {
                return flysqlKey.getMongoTemplate().find(new Query(parseSqlConditions(sqlConditions)), flysqlKey.getTargetClass(), Objects.requireNonNull(FlysqlUtils.getTableName(flysqlKey.getTargetClass())));
            } else {
                String sqlString = parseSqlConditions(initSelectSql(), sqlConditions);
                log.debug("[oxygen-flysql-sql]:" + sqlString);
                return flysqlKey.getJdbcTemplate().query(sqlString, new BeanPropertyRowMapper<>(flysqlKey.getTargetClass()));
            }
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

        String sqlString = parseSqlConditions(initUpdateSql(), sqlConditions);
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
            if (flysqlKey.getJdbcTemplate() == null) {
                flysqlKey.getMongoTemplate().save(entity, Objects.requireNonNull(FlysqlUtils.getTableName(flysqlKey.getTargetClass())));
            } else {
                flysqlKey.getJdbcTemplate().execute(sqlString);
            }
        } catch (Exception e) {
            throw new FlysqlException(e.getMessage());
        }
    }

    @Override
    public void doDelete() {

        String sqlString = parseSqlConditions(initDeleteSql(), sqlConditions);
        log.debug("[oxygen-flysql-sql]:" + sqlString);

        try {
            flysqlKey.getJdbcTemplate().update(sqlString);
        } catch (Exception e) {
            throw new FlysqlException(e.getMessage());
        }
    }

    @Override
    public Integer count() {

        String sqlString = parseSqlConditions(initCountSql(), sqlConditions);
        log.debug("[oxygen-flysql-sql]:" + sqlString);

        try {
            return flysqlKey.getJdbcTemplate().queryForObject(sqlString, Integer.class);
        } catch (Exception e) {
            throw new FlysqlException(e.getMessage());
        }
    }

    // ---------------------------------------- init sql ----------------------------------------

    /**
     * init count sql
     *
     * @return sqlString
     * @since 0.0.1
     */
    public String initCountSql() {

        return "select count(1) from " + FlysqlUtils.getTableName(flysqlKey.getTargetClass());
    }

    /**
     * init delete sql
     *
     * @return sqlString
     * @since 0.0.1
     */
    public String initDeleteSql() {

        return "delete from " + FlysqlUtils.getTableName(flysqlKey.getTargetClass());
    }

    /**
     * init update sql
     *
     * @return sqlString
     * @since 0.0.1
     */
    public String initUpdateSql() {

        StringBuilder sqlStringBuilder = new StringBuilder("update " + FlysqlUtils.getTableName(flysqlKey.getTargetClass()) + " set ");

        for (SqlCondition sqlConditionMeta : sqlConditions) {
            if (sqlConditionMeta.getOperateType().equals(UPDATE)) {
                Object value = sqlConditionMeta.getValue();
                if (value == null) {
                    sqlStringBuilder.append(sqlConditionMeta.getColumnName()).append(" = null");
                } else {
                    sqlStringBuilder.append(sqlConditionMeta.getColumnName()).append(" = ").append(value);
                }
            }
        }
        return sqlStringBuilder.toString();
    }

    /**
     * init select sql
     *
     * @return sqlString
     * @since 0.0.1
     */
    public String initSelectSql() {

        if (flysqlKey.getSqlType().equals(SqlType.VIEW)) {

            // duplicate view
            if (flysqlKey.getTargetClass().isAnnotationPresent(FlysqlViews.class)) {

                FlysqlView[] flysqlViews = flysqlKey.getTargetClass().getAnnotation(FlysqlViews.class).value();
                for (FlysqlView metaFlysqlView : flysqlViews) {
                    if (flysqlKey.getViewSqlName().equals(metaFlysqlView.name())) {
                        return " select " + FlysqlConstants.SELECT_REPLACE_CONTENT + " from ( " + metaFlysqlView.value() + " ) flysql ";
                    }
                }
            }

            // singel view
            if (flysqlKey.getTargetClass().isAnnotationPresent(FlysqlView.class)) {

                FlysqlView flysqlView = flysqlKey.getTargetClass().getAnnotation(FlysqlView.class);
                return " select " + FlysqlConstants.SELECT_REPLACE_CONTENT + " from ( " + flysqlView.value() + " ) flysql ";
            }

            throw new FlysqlException("view is not exist");
        } else {

            // normal select sql
            String tableName = FlysqlUtils.getTableName(flysqlKey.getTargetClass());
            return tableName == null ? "" : "select " + FlysqlConstants.SELECT_REPLACE_CONTENT + " from " + tableName;
        }
    }

    /**
     * init insert sql
     *
     * @param entity entity
     * @return sqlString
     * @since 0.0.1
     */
    public String initSaveSql(Object entity) {

        List<String> columnList = new ArrayList<>();
        List<String> valueList = new ArrayList<>();

        List<FieldBody> fieldBodies = ReflectUtils.queryFields(flysqlKey.getTargetClass());

        for (FieldBody metaFieldBody : fieldBodies) {

            Object invoke;

            Field metaField = metaFieldBody.getField();
            if (metaField.isAnnotationPresent(CreatedBy.class) || metaField.isAnnotationPresent(LastModifiedBy.class)) {
                invoke = getExecutorId();
            } else if (metaField.isAnnotationPresent(CreatedDate.class) || metaField.isAnnotationPresent(LastModifiedDate.class)) {
                invoke = LocalDateTime.now();
            } else if (metaField.isAnnotationPresent(Version.class)) {
                invoke = 1;
            } else if (metaField.isAnnotationPresent(IsDelete.class)) {
                invoke = 0;
            } else {
                try {
                    invoke = metaFieldBody.getReadMethod().invoke(entity);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    continue;
                }
            }

            if (invoke != null) {
                columnList.add(columnsMap.get(metaField.getName()));
                if (metaField.getName().equals(ReflectConstants.BOOLEAN)) {
                    valueList.add(invoke.toString());
                } else {
                    valueList.add(FlysqlExecute.addSingleQuote(invoke));
                }
            }
        }

        String tableName = FlysqlUtils.getTableName(flysqlKey.getTargetClass());

        return "insert into " + tableName + " ( " + Strings.join(columnList, ',') + " ) values ( " + Strings.join(valueList, ',') + ")";
    }

    /**
     * get executor id
     *
     * @return user id
     * @since 0.0.1
     */
    public String getExecutorId() {

        if (SecurityContextHolder.getContext() == null || SecurityContextHolder.getContext().getAuthentication() == null) {
            return "anonymous";
        }

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal == null) {
            return "anonymous";
        }

        return String.valueOf(principal);
    }

    /**
     * parse mongodb sql conditions
     *
     * @param sqlConditions sqlConditions
     * @return sqlString
     * @since 0.0.1
     */
    public Criteria parseSqlConditions(List<SqlCondition> sqlConditions) {

        Criteria criteria = new Criteria();

        for (SqlCondition sqlConditionMeta : sqlConditions) {

            switch (sqlConditionMeta.getOperateType()) {

                case EQ:
                    criteria.and(sqlConditionMeta.getColumnName()).is(sqlConditionMeta.getValue());
                    break;
                case GT_EQ:
                    criteria.and(sqlConditionMeta.getColumnName()).gte(sqlConditionMeta.getValue());
                    break;
                case GT:
                    criteria.and(sqlConditionMeta.getColumnName()).gt(sqlConditionMeta.getValue());
                    break;
                case LT:
                    criteria.and(sqlConditionMeta.getColumnName()).lt(sqlConditionMeta.getValue());
                    break;
                case LT_EQ:
                    criteria.and(sqlConditionMeta.getColumnName()).lte(sqlConditionMeta.getValue());
                    break;
                case IN:
                    criteria.and(sqlConditionMeta.getColumnName()).in(sqlConditionMeta.getValue());
                    break;
                case NOT_IN:
                    criteria.and(sqlConditionMeta.getColumnName()).ne(sqlConditionMeta.getValue());
                    break;
                case OR:
                    criteria.orOperator();
                    break;
                case AND:
                    criteria.andOperator();
                    break;
            }

        }

        return criteria;
    }

    /**
     * parse sql conditions
     *
     * @param sqlString     sqlString
     * @param sqlConditions sqlConditions
     * @return sqlString
     * @since 0.0.1
     */
    public String parseSqlConditions(String sqlString, List<SqlCondition> sqlConditions) {

        StringBuilder sqlStringBuilder = new StringBuilder(sqlString);

        boolean selectFlag = true;

        SqlCondition sqlConditionTemp = null;

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
                    if (hasOperateType(sqlConditionTemp, SqlOperateType.ORDER_BY)) {
                        sqlStringBuilder.append(",");
                    } else {
                        sqlStringBuilder.append(" order by ");
                    }
                    sqlStringBuilder.append(sqlConditionMeta.getColumnName()).append(" ").append(sqlConditionMeta.getValue());
                    break;
                case UPDATE:
                    break;
                case SQL:
                    return sqlConditionMeta.getColumnName();
                default:
                    if (hasOperateType(sqlConditionTemp, UPDATE) || hasOperateType(sqlConditionTemp, SqlOperateType.SELECT) || hasOperateType(sqlConditionTemp, SqlOperateType.SET_VALUE)) {
                        sqlStringBuilder.append(" where ");
                    } else {
                        sqlStringBuilder.append(" and ");
                    }
                    sqlStringBuilder.append(sqlConditionMeta.getColumnName()).append(sqlConditionMeta.getOperateType().getCode()).append(sqlConditionMeta.getValue());
                    break;
            }
            sqlConditionTemp = sqlConditionMeta;
        }

        // 替换需要查询的字段
        if (selectFlag) {
            List<String> columnsList = new ArrayList<>();
            columnsMap.forEach((k, v) -> columnsList.add(v + " " + k));
            return sqlStringBuilder.toString().replace(FlysqlConstants.SELECT_REPLACE_CONTENT, Strings.join(columnsList, ','));
        }

        return sqlStringBuilder.toString();
    }

    /**
     * has use operate
     *
     * @param sqlCondition   sqlCondition
     * @param sqlOperateType sqlOperateType
     * @return true has used
     * @since 0.0.1
     */
    public static Boolean hasOperateType(SqlCondition sqlCondition, SqlOperateType sqlOperateType) {

        if (sqlCondition == null) {
            return true;
        }
        return sqlCondition.getOperateType().equals(sqlOperateType);
    }
}
