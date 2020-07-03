/*
 * Copyright [2020] [ispong]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ispong.oxygen.freecode.config;

import com.ispong.oxygen.freecode.controller.FreecodeController;
import com.ispong.oxygen.freecode.pojo.properties.FreecodeProperties;
import com.ispong.oxygen.freecode.repository.FreecodeRepository;
import com.ispong.oxygen.freecode.service.FreecodeService;
import com.ispong.oxygen.freecode.utils.FreecodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
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

    @Bean
    @ConditionalOnClass(FreecodeAutoConfiguration.class)
    public void initFreecodeBanner() {

        log.info("welcome to use oxygen-freecode");
    }

    
    @Bean
    @ConditionalOnClass(FreecodeAutoConfiguration.class)
    public FreecodeUtils initFreemarkerUtil(FreeMarkerConfigurer freeMarkerConfigurer) {

        return new FreecodeUtils(freeMarkerConfigurer);
    }

    @Bean
    @ConditionalOnClass(FreecodeAutoConfiguration.class)
    public FreecodeRepository initFreecodeRepository(JdbcTemplate jdbcTemplate) {

        log.debug("init freecode repository");

        return new FreecodeRepository(jdbcTemplate);
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
