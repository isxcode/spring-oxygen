package com.isxcode.oxygen.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;

/**
 * 项目基础配置
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019-11-14
 */
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
	@Bean
	public CommandLineRunner run(ApplicationContext appContext) {
		return args -> Arrays.stream(appContext.getBeanDefinitionNames()).sorted().forEach(System.out::println);
	}

}


