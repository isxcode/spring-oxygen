package com.ispong.oxygen.config;

//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import com.ispong.oxygen.flysql.annotation.EnableFlysql;
import com.ispong.oxygen.freecode.annotation.EnableFreecode;
import com.ispong.oxygen.module.file.FileProperties;
import com.ispong.oxygen.scheduler.QuartzProperties;
import com.ispong.oxygen.wechatgo.annotation.EnableWechatgo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

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
//@EnableAspectJAutoProxy
//@EnableAsync
//@ImportResource(locations = {"classpath:schema/**"})
public class AppConfig {

}


