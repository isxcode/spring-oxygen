package com.ispong.oxygen.flysql;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;

@Slf4j
public class FlysqlAutoConfiguration {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Bean
    @ConditionalOnClass(FlysqlAutoConfiguration.class)
    private FlySqlFactory initFlySqlFactory() {

        return new FlySqlFactory(jdbcTemplate);
    }

}
