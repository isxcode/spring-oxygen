package com.ispong.oxygen.flysql;

import lombok.Data;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
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
     * spring 自带的数据源属性
     */
    private Map<String, DataSourceProperties> datasource;
}
