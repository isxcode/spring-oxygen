package com.isxcode.oxygen.flysql.core;

import com.isxcode.oxygen.flysql.constant.FlysqlConstants;
import com.isxcode.oxygen.flysql.enums.DataBaseType;
import com.isxcode.oxygen.flysql.exception.FlysqlException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 负责初始化所有的template,并生成对应的builder
 *
 * @author ispong
 * @version 0.0.2
 */
public class Flysql {

    /**
     * 初始化flysql时,存储jdbcTemplate
     */
    private final Map<String, JdbcTemplate> jdbcTemplateMap;

    /**
     * 初始化flysql时,存储mongoTemplate
     */
    private final Map<String, MongoTemplate> mongdTemplateMap;

    public Flysql(Map<String, JdbcTemplate> jdbcTemplateMap,
                  Map<String, MongoTemplate> mongdTemplateMap) {

        this.mongdTemplateMap = mongdTemplateMap;
        this.jdbcTemplateMap = jdbcTemplateMap;
    }

    /**
     * 构建数据源
     *
     * @param dataBaseType 数据源类型
     * @param dataBaseName 数据源名称
     * @return FlysqlBuilder
     */
    public FlysqlBuilder build(DataBaseType dataBaseType, String dataBaseName) {

        switch (dataBaseType) {
            case MONGO:
                return new FlysqlBuilder(dataBaseType, mongdTemplateMap.get(dataBaseName));
            case ORACLE:
            case H2:
            case MYSQL:
                return new FlysqlBuilder(dataBaseType, jdbcTemplateMap.get(dataBaseName));
            default:
                throw new FlysqlException("数据库类型暂不支持");
        }
    }

    /**
     * 构建数据源
     * 默认数据源为Mysql
     *
     * @param dataBaseName 数据源名称
     * @return FlysqlBuilder
     */
    public FlysqlBuilder build(String dataBaseName) {

        return build(DataBaseType.MYSQL, dataBaseName);
    }

    /**
     * 构建数据源
     * 默认数据源名称为FlysqlConstants.PRIMARY_DATASOURCE_NAME
     *
     * @param dataBaseType 数据源类型
     * @return FlysqlBuilder
     */
    public FlysqlBuilder build(DataBaseType dataBaseType) {

        return build(dataBaseType, FlysqlConstants.PRIMARY_DATASOURCE_NAME);
    }

    /**
     * 构建数据源
     * 默认数据源为Mysql,且默认数据源名为FlysqlConstants.PRIMARY_DATASOURCE_NAME
     *
     * @return FlysqlBuilder
     */
    public FlysqlBuilder build() {

        return build(DataBaseType.MYSQL, FlysqlConstants.PRIMARY_DATASOURCE_NAME);
    }

    /**
     * 获取用户默认数据源
     *
     * @return DataSource
     */
    public DataSource getDefaultDataSource() {

        return jdbcTemplateMap.get(FlysqlConstants.PRIMARY_DATASOURCE_NAME).getDataSource();
    }
}
