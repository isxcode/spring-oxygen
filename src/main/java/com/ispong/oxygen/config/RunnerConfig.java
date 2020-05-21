package com.ispong.oxygen.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

/**
 * 项目启动运行配置文件
 *
 * @author ispong
 * @since 0.0.1
 */
//@Component
public class RunnerConfig implements CommandLineRunner {

    private final ApplicationContext applicationContext;

    public RunnerConfig(ApplicationContext applicationContext) {

        this.applicationContext = applicationContext;
    }

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(applicationContext.getBeanDefinitionNames()).sorted().forEach(System.out::println);
    }
}