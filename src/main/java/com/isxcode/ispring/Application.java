package com.isxcode.ispring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * isxcode 控制器
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019/10/20
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * 打印applicationContext内容
	 *
	 * @since 2019/10/17
	 */
	@Bean
	public CommandLineRunner run(ApplicationContext appContext) {
		return args -> {
//			String[] beans = appContext.getBeanDefinitionNames();
//			Arrays.stream(beans).sorted().forEach(System.out::println);
		};
	}

}



