package com.isxcode.oxygen.flysql.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * 配置文件
 *
 * @author ispong
 * @since 0.0.1
 */
@Data
@ConfigurationProperties("oxygen.flysql")
public class FlysqlDataSourceProperties {

    /**
     *
     */
    private Map<String, DataSourceProperties> datasource;

    /**
     * 配置mongodb
     */
    private Map<String, MongoProperties> mongodb;

    /**
     * 打印日志 默认打印日志
     */
    private Boolean showLog = true;

}
