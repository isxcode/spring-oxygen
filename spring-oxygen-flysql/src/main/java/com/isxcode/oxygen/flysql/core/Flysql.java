package com.isxcode.oxygen.flysql.core;

import com.isxcode.oxygen.flysql.constant.FlysqlConstants;
import com.isxcode.oxygen.flysql.entity.FlysqlKey;
import com.isxcode.oxygen.flysql.enums.SqlType;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Flysql factory
 *
 * @author ispong
 * @version 0.0.1
 */
public class Flysql {

    /**
     * store datasource list
     */
    private static Map<String, JdbcTemplate> jdbcTemplateMap;

    public Flysql(Map<String, JdbcTemplate> jdbcTemplateMap) {

        Flysql.jdbcTemplateMap = jdbcTemplateMap;
    }

    /**
     * get default datasource
     *
     * @return DataSource
     */
    public static DataSource getDefaultDataSource() {

        return jdbcTemplateMap.get(FlysqlConstants.PRIMARY_DATASOURCE_NAME).getDataSource();
    }

    /**
     * insert builder
     *
     * @param <A>            A
     * @param targetClass    targetClass
     * @param dataSourceName dataSourceName
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public static <A> FlysqlBuilder<A> insert(String dataSourceName, Class<A> targetClass) {

        return new FlysqlBuilder<>(new FlysqlKey<>(SqlType.INSERT, jdbcTemplateMap.get(dataSourceName), targetClass));
    }

    /**
     * insert builder
     *
     * @param targetClass targetClass
     * @param <A>         A
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public static <A> FlysqlBuilder<A> insert(Class<A> targetClass) {

        return insert(FlysqlConstants.PRIMARY_DATASOURCE_NAME, targetClass);
    }

    /**
     * delete builder
     *
     * @param targetClass    targetClass
     * @param dataSourceName dataSourceName
     * @param <A>            A
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public static <A> FlysqlBuilder<A> delete(String dataSourceName, Class<A> targetClass) {

        return new FlysqlBuilder<>(new FlysqlKey<>(SqlType.DELETE, jdbcTemplateMap.get(dataSourceName), targetClass));
    }

    /**
     * delete builder
     *
     * @param targetClass targetClass
     * @param <A>         A
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public static <A> FlysqlBuilder<A> delete(Class<A> targetClass) {

        return delete(FlysqlConstants.PRIMARY_DATASOURCE_NAME, targetClass);
    }

    /**
     * update builder
     *
     * @param targetClass    targetClass
     * @param dataSourceName dataSourceName
     * @param <A>            A
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public static <A> FlysqlBuilder<A> update(String dataSourceName, Class<A> targetClass) {

        return new FlysqlBuilder<>(new FlysqlKey<>(SqlType.UPDATE, jdbcTemplateMap.get(dataSourceName), targetClass));
    }

    /**
     * update builder
     *
     * @param targetClass targetClass
     * @param <A>         A
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public static <A> FlysqlBuilder<A> update(Class<A> targetClass) {

        return update(FlysqlConstants.PRIMARY_DATASOURCE_NAME, targetClass);
    }

    /**
     * view builder
     *
     * @param targetClass    targetClass
     * @param dataSourceName dataSourceName
     * @param viewName       viewName
     * @param <A>            A
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public static <A> FlysqlBuilder<A> view(String dataSourceName, String viewName, Class<A> targetClass) {

        return new FlysqlBuilder<>(new FlysqlKey<>(SqlType.VIEW, jdbcTemplateMap.get(dataSourceName), targetClass, viewName));
    }

    /**
     * view builder
     *
     * @param targetClass targetClass
     * @param viewName    viewName
     * @param <A>         A
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public static <A> FlysqlBuilder<A> view(String viewName, Class<A> targetClass) {

        return view(FlysqlConstants.PRIMARY_DATASOURCE_NAME, viewName, targetClass);
    }

    /**
     * select builder
     *
     * @param targetClass    targetClass
     * @param dataSourceName dataSourceName
     * @param <A>            A
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public static <A> FlysqlBuilder<A> select(String dataSourceName, Class<A> targetClass) {

        return new FlysqlBuilder<>(new FlysqlKey<>(SqlType.SELECT, jdbcTemplateMap.get(dataSourceName), targetClass));
    }

    /**
     * select builder
     *
     * @param targetClass targetClass
     * @param <A>         A
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public static <A> FlysqlBuilder<A> select(Class<A> targetClass) {

        return select(FlysqlConstants.PRIMARY_DATASOURCE_NAME, targetClass);
    }

}
