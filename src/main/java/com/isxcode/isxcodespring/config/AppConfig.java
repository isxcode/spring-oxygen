package com.isxcode.isxcodespring.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;


/**
 * 项目总配置文件配置
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019-11-11
 */
@Configuration
@ImportResource(locations = {"classpath:aop.xml"})
public class AppConfig {

}
