package com.ispong.oxygen.scheduler;

import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 配置quartz定时器
 *
 * @author ispong
 * @since 0.0.1
 */
@Configuration
public class QuartzConfig {

    /**
     * 初始化quartz数据库
     *
     * @param quartzProperties 配置文件
     * @return datasource
     * @since 0.0.1
     */
    @Bean
    @QuartzDataSource
    public DataSource initQuartzDataSource(QuartzProperties quartzProperties) {

        return quartzProperties.getDatasource().initializeDataSourceBuilder().build();
    }

}
