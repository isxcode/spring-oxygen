package com.ispong.oxygen.flysql;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;

/**
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
public class FlysqlAutoConfiguration {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Bean
    @ConditionalOnClass(FlysqlAutoConfiguration.class)
    private void initBanner(){
        log.debug("init banner");
        System.out.print("                                                     _____.__                     .__   \n");
        System.out.print("  _______  ______.__. ____   ____   ____           _/ ____\\  | ___.__. ___________|  |  \n");
        System.out.print(" /  _ \\  \\/  <   |  |/ ___\\_/ __ \\ /    \\   ______ \\   __\\|  |<   |  |/  ___/ ____/  |  \n");
        System.out.print("(  <_> >    < \\___  / /_/  >  ___/|   |  \\ /_____/  |  |  |  |_\\___  |\\___ < <_|  |  |__\n");
        System.out.print(" \\____/__/\\_ \\/ ____\\___  / \\___  >___|  /          |__|  |____/ ____/____  >__   |____/\n");
        System.out.print("            \\/\\/   /_____/      \\/     \\/                      \\/         \\/   |__|     \n");
    }

    @Bean
    @ConditionalOnClass(FlysqlAutoConfiguration.class)
    private FlySqlFactory initFlySqlFactory() {

        return new FlySqlFactory(jdbcTemplate);
    }

}
