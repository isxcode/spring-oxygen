package com.isxcode.oxygen.config;

import com.isxcode.oxygen.wechatgo.annotation.EnableWechatgo;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * 项目基础配置
 *
 * @author ispong
 * @version v0.1.0
 */
@EnableWechatgo
@EnableJpaRepositories(basePackages = {"com.isxcode.oxygen.repositories"})
@EnableJpaAuditing
@EnableConfigurationProperties
@EnableTransactionManagement
@Configuration
@EnableAspectJAutoProxy
@EnableScheduling
@EnableAsync
@ImportResource(locations = {"classpath:schema/**"})
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


