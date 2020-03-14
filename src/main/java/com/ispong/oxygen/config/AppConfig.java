package com.ispong.oxygen.config;

//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import com.ispong.oxygen.flysql.annotation.EnableFlysql;
import com.ispong.oxygen.wechatgo.annotation.EnableWechatgo;
import org.springframework.context.annotation.Configuration;

/**
 * 项目基础配置
 *
 * @author ispong
 * @version v0.1.0
 */
@EnableWechatgo
@EnableFlysql
@Configuration
//@EnableConfigurationProperties
//@EnableTransactionManagement
//@EnableAspectJAutoProxy
//@EnableScheduling
//@EnableAsync
//@ImportResource(locations = {"classpath:schema/**"})
public class AppConfig {

	/**
	 * 打印ApplicationContext
	 *
	 * @param appContext ApplicationContext
	 * @since 2019/10/17
	 */
//	@Bean
//	public CommandLineRunner run(ApplicationContext appContext) {
//		return args -> Arrays.stream(appContext.getBeanDefinitionNames()).sorted().forEach(System.out::println);
//	}

}


