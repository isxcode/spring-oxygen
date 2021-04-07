package com.isxcode.oxygen.flysql.core;

import com.isxcode.oxygen.flysql.entity.FlysqlKey;
import com.isxcode.oxygen.flysql.enums.DataBaseType;
import com.isxcode.oxygen.flysql.enums.SqlType;
import com.isxcode.oxygen.flysql.exception.FlysqlException;
import com.isxcode.oxygen.flysql.properties.FlysqlDataSourceProperties;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 初始化flysql构建对象
 *
 * @author ispong
 * @version 0.0.1
 */
public class FlysqlBuilder {

    private JdbcTemplate jdbcTemplate;

    private MongoTemplate mongoTemplate;

    private final DataBaseType dataBaseType;

    private final FlysqlDataSourceProperties flysqlDataSourceProperties;

    public FlysqlBuilder(DataBaseType dataBaseType, MongoTemplate mongoTemplate, FlysqlDataSourceProperties flysqlDataSourceProperties) {

        this.dataBaseType = dataBaseType;
        this.mongoTemplate = mongoTemplate;
        this.flysqlDataSourceProperties = flysqlDataSourceProperties;
    }

    public FlysqlBuilder(DataBaseType dataBaseType, JdbcTemplate jdbcTemplate, FlysqlDataSourceProperties flysqlDataSourceProperties) {

        this.dataBaseType = dataBaseType;
        this.jdbcTemplate = jdbcTemplate;
        this.flysqlDataSourceProperties = flysqlDataSourceProperties;
    }

    /**
     * insert builder
     *
     * @param <A>         A
     * @param targetClass targetClass
     * @return FlysqlBuilder
     * @since 0.0.2
     */
    public <A> FlysqlExecute<A> insert(Class<A> targetClass) {

        switch (dataBaseType) {
            case MONGO:
                return new FlysqlExecute<>(new FlysqlKey<>(DataBaseType.MONGO, SqlType.INSERT, mongoTemplate, targetClass, flysqlDataSourceProperties));
            case ORACLE:
            case H2:
            case MYSQL:
                return new FlysqlExecute<>(new FlysqlKey<>(DataBaseType.MYSQL, SqlType.INSERT, jdbcTemplate, targetClass, flysqlDataSourceProperties));
            default:
                throw new FlysqlException("数据库类型暂不支持");
        }
    }


    /**
     * delete builder
     *
     * @param <A>         A
     * @param targetClass targetClass
     * @return FlysqlBuilder
     * @since 0.0.2
     */
    public <A> FlysqlExecute<A> delete(Class<A> targetClass) {

        switch (dataBaseType) {
            case MONGO:
                return new FlysqlExecute<>(new FlysqlKey<>(DataBaseType.MONGO, SqlType.DELETE, mongoTemplate, targetClass, flysqlDataSourceProperties));
            case ORACLE:
            case H2:
            case MYSQL:
                return new FlysqlExecute<>(new FlysqlKey<>(DataBaseType.MYSQL, SqlType.DELETE, jdbcTemplate, targetClass, flysqlDataSourceProperties));
            default:
                throw new FlysqlException("数据库类型暂不支持");
        }
    }


    /**
     * update builder
     *
     * @param <A>         A
     * @param targetClass targetClass
     * @return FlysqlBuilder
     * @since 0.0.2
     */
    public <A> FlysqlExecute<A> update(Class<A> targetClass) {

        switch (dataBaseType) {
            case MONGO:
                return new FlysqlExecute<>(new FlysqlKey<>(DataBaseType.MONGO, SqlType.UPDATE, mongoTemplate, targetClass, flysqlDataSourceProperties));
            case ORACLE:
            case H2:
            case MYSQL:
                return new FlysqlExecute<>(new FlysqlKey<>(DataBaseType.MYSQL, SqlType.UPDATE, jdbcTemplate, targetClass, flysqlDataSourceProperties));
            default:
                throw new FlysqlException("数据库类型暂不支持");
        }
    }


    /**
     * view builder
     *
     * @param <A>         A
     * @param targetClass targetClass
     * @param viewName    viewName
     * @return FlysqlBuilder
     * @since 0.0.2
     */
    public <A> FlysqlExecute<A> view(String viewName, Class<A> targetClass) {

        switch (dataBaseType) {
            case ORACLE:
            case H2:
            case MYSQL:
                return new FlysqlExecute<>(new FlysqlKey<>(DataBaseType.MYSQL, SqlType.VIEW, jdbcTemplate, targetClass, viewName, flysqlDataSourceProperties));
            case MONGO:
            default:
                throw new FlysqlException("数据库类型暂不支持");
        }

    }

    /**
     * select builder
     *
     * @param <A>         A
     * @param targetClass targetClass
     * @return FlysqlBuilder
     * @since 0.0.2
     */
    public <A> FlysqlExecute<A> select(Class<A> targetClass) {

        switch (dataBaseType) {
            case MONGO:
                return new FlysqlExecute<>(new FlysqlKey<>(DataBaseType.MONGO, SqlType.SELECT, mongoTemplate, targetClass, flysqlDataSourceProperties));
            case ORACLE:
            case H2:
            case MYSQL:
                return new FlysqlExecute<>(new FlysqlKey<>(DataBaseType.MYSQL, SqlType.SELECT, jdbcTemplate, targetClass, flysqlDataSourceProperties));
            default:
                throw new FlysqlException("数据库类型暂不支持");
        }
    }

}
