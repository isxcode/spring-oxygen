package com.isxcode.oxygen.flysql.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * config flysql
 *
 * @author ispong
 * @since 0.0.1
 */
@Data
@ConfigurationProperties("oxygen.flysql")
public class FlysqlProperties {

    /**
     * jdbc properties
     */
    private Map<String, DataSourceProperties> datasource;

    /**
     * mongo properties
     */
    private Map<String, MongoProperties> mongodb;

    /**
     * show logs
     */
    private Boolean showLog = true;
}
