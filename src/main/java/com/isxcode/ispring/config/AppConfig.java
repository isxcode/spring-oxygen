package com.isxcode.ispring.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 项目基础配置
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019-11-14
 */
@Configuration
@EnableConfigurationProperties(PropertiesConfig.class)
@EnableAspectJAutoProxy
@EnableScheduling
@ImportResource(locations = {"classpath:schema/beans.xml", "classpath:schema/context.xml"})
public class AppConfig {

}
