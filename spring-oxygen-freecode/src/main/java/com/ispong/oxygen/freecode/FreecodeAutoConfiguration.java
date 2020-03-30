package com.ispong.oxygen.freecode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@Slf4j
@EnableConfigurationProperties(FreecodeProperties.class)
public class FreecodeAutoConfiguration {


    @Bean
    @ConditionalOnClass(FreecodeAutoConfiguration.class)
    public FreecodeService initFreecodeService(){
        return new FreecodeService();
    }

//    @Bean
//    @ConditionalOnBean(FreecodeService.class)
//    public FreecodeController initWechatgoController(FreecodeService freecodeService) {
//
//        log.debug("init freecode controller");
//        return new FreecodeController(freecodeService);
//    }

}
