package com.ispong.oxygen.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 配置druid可识别数据源
 *
 * @author ispong
 * @since 0.0.1
 */
@Configuration
public class DruidConfig {

    /**
     * 注入阿里druid可以识别的数据源
     *
     * @since 0.0.1
     */
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource druidDataSource() {

        return new DruidDataSource();
    }
}
