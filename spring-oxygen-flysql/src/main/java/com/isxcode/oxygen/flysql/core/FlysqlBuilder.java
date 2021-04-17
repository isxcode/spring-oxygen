package com.isxcode.oxygen.flysql.core;

import com.isxcode.oxygen.flysql.constant.FlysqlConstants;
import com.isxcode.oxygen.flysql.entity.FlysqlKey;
import com.isxcode.oxygen.flysql.enums.DataBaseType;
import com.isxcode.oxygen.flysql.enums.SqlType;
import com.isxcode.oxygen.flysql.exception.FlysqlException;
import com.isxcode.oxygen.flysql.properties.FlysqlProperties;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.Objects;

/**
 * init flysql builder
 *
 * @author ispong
 * @version 0.0.1
 */
public class FlysqlBuilder {

    private JdbcTemplate jdbcTemplate;

    private MongoTemplate mongoTemplate;

    private final FlysqlProperties flysqlProperties;

    public FlysqlBuilder(MongoTemplate mongoTemplate, FlysqlProperties flysqlProperties) {

        this.mongoTemplate = mongoTemplate;
        this.flysqlProperties = flysqlProperties;
    }

    public FlysqlBuilder(JdbcTemplate jdbcTemplate, FlysqlProperties flysqlProperties) {

        this.jdbcTemplate = jdbcTemplate;
        this.flysqlProperties = flysqlProperties;
    }

    public DataBaseType getDataBaseType() {

        if (mongoTemplate != null) {
            return DataBaseType.MONGO;
        } else {
            try {
                String databaseName = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection().getMetaData().getDatabaseProductName();
                switch (databaseName) {
                    case FlysqlConstants.ORACLE_DB:
                        return DataBaseType.ORACLE;
                    case FlysqlConstants.H2_DB:
                        return DataBaseType.H2;
                    case FlysqlConstants.MYSQL_DB:
                        return DataBaseType.MYSQL;
                }
            } catch (SQLException e) {
                throw new FlysqlException("datasource link error");
            }
        }
        throw new FlysqlException("datasource link error");
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

        if (jdbcTemplate == null) {
            return new FlysqlExecute<>(new FlysqlKey<>(DataBaseType.MONGO, SqlType.INSERT, mongoTemplate, targetClass, flysqlProperties));
        } else {
            return new FlysqlExecute<>(new FlysqlKey<>(getDataBaseType(), SqlType.INSERT, jdbcTemplate, targetClass, flysqlProperties));
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

        if (jdbcTemplate == null) {
            return new FlysqlExecute<>(new FlysqlKey<>(DataBaseType.MONGO, SqlType.DELETE, mongoTemplate, targetClass, flysqlProperties));
        } else {
            return new FlysqlExecute<>(new FlysqlKey<>(getDataBaseType(), SqlType.DELETE, jdbcTemplate, targetClass, flysqlProperties));
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

        if (jdbcTemplate == null) {
            return new FlysqlExecute<>(new FlysqlKey<>(DataBaseType.MONGO, SqlType.UPDATE, mongoTemplate, targetClass, flysqlProperties));
        } else {
            return new FlysqlExecute<>(new FlysqlKey<>(getDataBaseType(), SqlType.UPDATE, jdbcTemplate, targetClass, flysqlProperties));
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

        if (jdbcTemplate == null) {
            return new FlysqlExecute<>(new FlysqlKey<>(DataBaseType.MONGO, SqlType.VIEW, mongoTemplate, targetClass, flysqlProperties));
        } else {
            return new FlysqlExecute<>(new FlysqlKey<>(getDataBaseType(), SqlType.VIEW, jdbcTemplate, targetClass, flysqlProperties));
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

        if (jdbcTemplate == null) {
            return new FlysqlExecute<>(new FlysqlKey<>(DataBaseType.MONGO, SqlType.SELECT, mongoTemplate, targetClass, flysqlProperties));
        } else {
            return new FlysqlExecute<>(new FlysqlKey<>(getDataBaseType(), SqlType.SELECT, jdbcTemplate, targetClass, flysqlProperties));
        }
    }

}
