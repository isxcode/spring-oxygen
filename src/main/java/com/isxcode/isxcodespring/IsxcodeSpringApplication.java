package com.isxcode.isxcodespring;

import com.isxcode.isxcodespring.model.properties.IsxcodeProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * isxcode 控制器
 *
 * @author ispong
 * @date 2019/10/20
 * @version v0.1.0
 */
@SpringBootApplication
@EnableConfigurationProperties(IsxcodeProperties.class)
public class IsxcodeSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsxcodeSpringApplication.class, args);
	}

//	/**
//	 * 打印applicationContext内容
//	 *
//	 * @since 2019/10/17
//	 */
//	@Bean
//	public CommandLineRunner run(ApplicationContext appContext) {
//		return args -> {
//			String[] beans = appContext.getBeanDefinitionNames();
//			Arrays.stream(beans).sorted().forEach(System.out::println);
//		};
//	}

}
