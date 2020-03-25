package com.ispong.oxygen.config;

//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import com.ispong.oxygen.flysql.annotation.EnableFlysql;
import com.ispong.oxygen.properties.OxygenProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 项目基础配置
 *
 * @author ispong
 * @version v0.1.0
 */
//@EnableWechatgo
@EnableFlysql
@Configuration
//@EnableConfigurationProperties
//@EnableTransactionManagement
//@EnableAspectJAutoProxy
//@EnableScheduling
//@EnableAsync
@EnableConfigurationProperties(OxygenProperties.class)
//@ImportResource(locations = {"classpath:schema/**"})
public class AppConfig {


}


