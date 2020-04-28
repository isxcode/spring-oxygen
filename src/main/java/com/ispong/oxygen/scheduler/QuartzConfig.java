package com.ispong.oxygen.scheduler;

import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

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


    /**
     * 注入一个新的TaskScheduler 给websocket调用
     *
     * @since 0.0.1
     */
    @Bean
    public TaskScheduler taskScheduler() {

        ThreadPoolTaskScheduler scheduling = new ThreadPoolTaskScheduler();
        scheduling.setPoolSize(10);
        scheduling.initialize();
        return scheduling;
    }
}
