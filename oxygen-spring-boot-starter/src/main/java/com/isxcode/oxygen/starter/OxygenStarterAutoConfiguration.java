package com.isxcode.oxygen.starter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

/**
 * 打印logo
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
public class OxygenStarterAutoConfiguration {

    /**
     * 打印启动logo
     *
     * @since 0.0.1
     */
    @Bean
    @ConditionalOnClass(OxygenStarterAutoConfiguration.class)
    private void initOxygenBanner() {

        log.debug("welcome to use spring-oxygen");
        System.out.println("   _____            _                   ____                            ");
        System.out.println("  / ___/____  _____(_)___  ____ _      / __ \\_  ____  ______ ____  ____ ");
        System.out.println("  \\__ \\/ __ \\/ ___/ / __ \\/ __ `/_____/ / / / |/_/ / / / __ `/ _ \\/ __ \\");
        System.out.println(" ___/ / /_/ / /  / / / / / /_/ /_____/ /_/ />  </ /_/ / /_/ /  __/ / / /");
        System.out.println("/____/ .___/_/  /_/_/ /_/\\__, /      \\____/_/|_|\\__, /\\__, /\\___/_/ /_/ ");
        System.out.println("    /_/                 /____/                 /____//____/             ");
    }

}
