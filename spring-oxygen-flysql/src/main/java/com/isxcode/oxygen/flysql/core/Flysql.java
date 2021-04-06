package com.isxcode.oxygen.flysql.core;

import com.isxcode.oxygen.flysql.constant.FlysqlConstants;
import com.isxcode.oxygen.flysql.entity.FlysqlKey;
import com.isxcode.oxygen.flysql.enums.DataBaseType;
import com.isxcode.oxygen.flysql.enums.SqlType;
import org.springframework.data.mongodb.core.MongoTemplate;
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
    private final Map<String, JdbcTemplate> jdbcTemplateMap;

    private final Map<String, MongoTemplate> mongdTemplateMap;

    public Flysql(Map<String, JdbcTemplate> jdbcTemplateMap,
                  Map<String, MongoTemplate> mongdTemplateMap) {

        this.mongdTemplateMap = mongdTemplateMap;
        this.jdbcTemplateMap = jdbcTemplateMap;
    }

    /**
     * get default datasource
     *
     * @return DataSource
     */
    public DataSource getDefaultDataSource() {

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
    public <A> FlysqlBuilder<A> insert(String dataSourceName, Class<A> targetClass) {

        return new FlysqlBuilder<>(new FlysqlKey<>(DataBaseType.MYSQL, SqlType.INSERT, jdbcTemplateMap.get(dataSourceName), targetClass));
    }

    /**
     * insert builder
     *
     * @param targetClass targetClass
     * @param <A>         A
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public <A> FlysqlBuilder<A> insert(Class<A> targetClass) {

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
    public <A> FlysqlBuilder<A> delete(String dataSourceName, Class<A> targetClass) {

        return new FlysqlBuilder<>(new FlysqlKey<>(DataBaseType.MYSQL, SqlType.DELETE, jdbcTemplateMap.get(dataSourceName), targetClass));
    }

    /**
     * delete builder
     *
     * @param targetClass targetClass
     * @param <A>         A
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public <A> FlysqlBuilder<A> delete(Class<A> targetClass) {

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
    public <A> FlysqlBuilder<A> update(String dataSourceName, Class<A> targetClass) {

        return new FlysqlBuilder<>(new FlysqlKey<>(DataBaseType.MYSQL, SqlType.UPDATE, jdbcTemplateMap.get(dataSourceName), targetClass));
    }

    /**
     * update builder
     *
     * @param targetClass targetClass
     * @param <A>         A
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public <A> FlysqlBuilder<A> update(Class<A> targetClass) {

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
    public <A> FlysqlBuilder<A> view(String dataSourceName, String viewName, Class<A> targetClass) {

        return new FlysqlBuilder<>(new FlysqlKey<>(DataBaseType.MYSQL, SqlType.VIEW, jdbcTemplateMap.get(dataSourceName), targetClass, viewName));
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
    public <A> FlysqlBuilder<A> view(String viewName, Class<A> targetClass) {

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
    public <A> FlysqlBuilder<A> select(String dataSourceName, Class<A> targetClass) {

        return new FlysqlBuilder<>(new FlysqlKey<>(DataBaseType.MYSQL, SqlType.SELECT, jdbcTemplateMap.get(dataSourceName), targetClass));
    }

    /**
     * select builder
     *
     * @param targetClass targetClass
     * @param <A>         A
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public <A> FlysqlBuilder<A> select(Class<A> targetClass) {

        return select(FlysqlConstants.PRIMARY_DATASOURCE_NAME, targetClass);
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
    public <A> FlysqlBuilder<A> mongoInsert(String dataSourceName, Class<A> targetClass) {

        return new FlysqlBuilder<>(new FlysqlKey<>(DataBaseType.MONGO, SqlType.INSERT, mongdTemplateMap.get(dataSourceName), targetClass));
    }

    /**
     * insert builder
     *
     * @param targetClass targetClass
     * @param <A>         A
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public <A> FlysqlBuilder<A> mongoInsert(Class<A> targetClass) {

        return mongoInsert(FlysqlConstants.PRIMARY_DATASOURCE_NAME, targetClass);
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
    public <A> FlysqlBuilder<A> mongoDelete(String dataSourceName, Class<A> targetClass) {

        return new FlysqlBuilder<>(new FlysqlKey<>(DataBaseType.MONGO, SqlType.DELETE, mongdTemplateMap.get(dataSourceName), targetClass));
    }

    /**
     * delete builder
     *
     * @param targetClass targetClass
     * @param <A>         A
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public <A> FlysqlBuilder<A> mongoDelete(Class<A> targetClass) {

        return mongoDelete(FlysqlConstants.PRIMARY_DATASOURCE_NAME, targetClass);
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
    public <A> FlysqlBuilder<A> mongoUpdate(String dataSourceName, Class<A> targetClass) {

        return new FlysqlBuilder<>(new FlysqlKey<>(DataBaseType.MONGO, SqlType.UPDATE, mongdTemplateMap.get(dataSourceName), targetClass));
    }

    /**
     * update builder
     *
     * @param targetClass targetClass
     * @param <A>         A
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public <A> FlysqlBuilder<A> mongoUpdate(Class<A> targetClass) {

        return mongoUpdate(FlysqlConstants.PRIMARY_DATASOURCE_NAME, targetClass);
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
    public <A> FlysqlBuilder<A> mongoView(String dataSourceName, String viewName, Class<A> targetClass) {

        return new FlysqlBuilder<>(new FlysqlKey<>(DataBaseType.MONGO, SqlType.VIEW, mongdTemplateMap.get(dataSourceName), targetClass, viewName));
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
    public <A> FlysqlBuilder<A> mongoView(String viewName, Class<A> targetClass) {

        return mongoView(FlysqlConstants.PRIMARY_DATASOURCE_NAME, viewName, targetClass);
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
    public <A> FlysqlBuilder<A> mongoSelect(String dataSourceName, Class<A> targetClass) {

        return new FlysqlBuilder<>(new FlysqlKey<>(DataBaseType.MONGO, SqlType.SELECT, mongdTemplateMap.get(dataSourceName), targetClass));
    }

    /**
     * select builder
     *
     * @param targetClass targetClass
     * @param <A>         A
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public <A> FlysqlBuilder<A> mongoSelect(Class<A> targetClass) {

        return mongoSelect(FlysqlConstants.PRIMARY_DATASOURCE_NAME, targetClass);
    }

}
