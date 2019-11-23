package com.isxcode.ispring.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 项目基础配置
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019-11-14
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.isxcode.ispring.repositories"})
@EnableTransactionManagement
@EnableConfigurationProperties
@EnableAspectJAutoProxy
@EnableScheduling
@ImportResource(locations = {"classpath:schema/**"})
public class AppConfig {

}

