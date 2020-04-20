package com.ispong.oxygen;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Calendar;

/**
 * 项目启动入口
 *
 * @author ispong
 * @version v0.1.0
 */
@RestController
@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

	/**
	 * 命令行
	 *
	 * @param appContext app上下文
	 * @since 0.0.1
	 */
	@Bean
	public CommandLineRunner run(ApplicationContext appContext) {

		return args -> Arrays.stream(appContext.getBeanDefinitionNames()).sorted().forEach(System.out::println);
	}
}

