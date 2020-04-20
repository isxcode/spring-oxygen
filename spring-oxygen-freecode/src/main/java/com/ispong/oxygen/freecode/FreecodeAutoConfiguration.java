package com.ispong.oxygen.freecode;

import com.ispong.oxygen.flysql.FlysqlAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Map;

/**
 * freecode 自动配置中心
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
@EnableConfigurationProperties(FreecodeProperties.class)
public class FreecodeAutoConfiguration {

    @Resource
    @Qualifier("oxygenDataSourceMap")
    private Map<String, DataSource> oxygenDataSourceMap;

    @Bean
    @ConditionalOnClass(FreecodeAutoConfiguration.class)
    public FreecodeUtils initFreemarkerUtil(FreeMarkerConfigurer freeMarkerConfigurer) {

        return new FreecodeUtils(freeMarkerConfigurer);
    }

    @Bean
    @ConditionalOnBean(name = "oxygenDataSourceMap")
    public FreecodeRepository initFreecodeRepository() {

        log.debug("init freecode repository");

        return new FreecodeRepository(oxygenDataSourceMap);
    }

    @Bean
    @ConditionalOnClass(FreecodeRepository.class)
    public FreecodeService initFreecodeService(FreecodeRepository freecodeRepository, FreecodeProperties freecodeProperties) {

        return new FreecodeService(freecodeRepository, freecodeProperties);
    }

    @Bean
    @ConditionalOnBean(FreecodeService.class)
    public FreecodeController initFreecodeController(FreecodeService freecodeService) {

        log.debug("init freecode controller");
        return new FreecodeController(freecodeService);
    }

}
