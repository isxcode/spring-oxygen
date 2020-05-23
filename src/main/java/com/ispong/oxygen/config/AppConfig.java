package com.ispong.oxygen.config;

//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import com.ispong.oxygen.flysql.annotation.EnableFlysql;
import com.ispong.oxygen.freecode.annotation.EnableFreecode;
import com.ispong.oxygen.wechatgo.annotation.EnableWechatgo;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 项目基础配置
 *
 * @author ispong
 * @version v0.1.0
 */
@EnableWechatgo
@EnableFlysql
@EnableFreecode
@Configuration
@EnableConfigurationProperties
@EnableTransactionManagement
@EnableScheduling
//@EnableDiscoveryClient
//@EnableAspectJAutoProxy
//@EnableAsync
//@ImportResource(locations = {"classpath:schema/**"})
public class AppConfig {

}


