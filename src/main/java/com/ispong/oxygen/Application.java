package com.ispong.oxygen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
	 * 测试项目启动接口
	 *
	 * @return 当前时间
	 * @since 0.0.1
	 */
	@GetMapping("/test")
	public String test() {

		return Calendar.getInstance().getTime().toString();
	}

//	/**
//	 * 命令行
//	 *
//	 * @param appContext app上下文
//	 * @since 0.0.1
//	 */
//	@Bean
//	public CommandLineRunner run(ApplicationContext appContext) {
//
//		return args -> Arrays.stream(appContext.getBeanDefinitionNames()).sorted().forEach(System.out::println);
//	}
}

