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
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 * freecode 配置中心/bean实例中心
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
@EnableConfigurationProperties(FreecodeProperties.class)
public class FreecodeAutoConfiguration {

    /**
     * banner 打印
     *
     * @since 0.0.1
     */
    @Bean
    @ConditionalOnClass(FreecodeAutoConfiguration.class)
    public void initFreecodeBanner() {

        log.info("welcome to use oxygen-freecode");
    }

    /**
     * 将freeMarker配置导入工具类
     *
     * @param freeMarkerConfigurer freemarker配置
     * @return 工具类实体
     * @since 0.0.1
     */
    @Bean
    @ConditionalOnClass(FreecodeAutoConfiguration.class)
    public FreecodeUtils initFreemarkerUtil(FreeMarkerConfigurer freeMarkerConfigurer) {

        return new FreecodeUtils(freeMarkerConfigurer);
    }

    /**
     * 将jdbcTemplate导入到Freecode的Repository中
     *
     * @param jdbcTemplate 数据源
     * @return FreecodeRepository
     * @since 0.0.1
     */
    @Bean
    @ConditionalOnClass(FreecodeAutoConfiguration.class)
    public FreecodeRepository initFreecodeRepository(JdbcTemplate jdbcTemplate) {

        log.debug("init freecode repository");

        return new FreecodeRepository(jdbcTemplate);
    }

    /**
     * 初始化service
     *
     * @param freecodeProperties 配置数据
     * @param freecodeRepository 数据层
     * @return FreecodeService
     * @since 0.0.1
     */
    @Bean
    @ConditionalOnClass(FreecodeRepository.class)
    public FreecodeService initFreecodeService(FreecodeRepository freecodeRepository,
                                               FreecodeProperties freecodeProperties) {

        return new FreecodeService(freecodeRepository, freecodeProperties);
    }

    /**
     * 初始化controller
     *
     * @param freecodeService 服务层
     * @return FreecodeController
     * @since 0.0.1
     */
    @Bean
    @ConditionalOnBean(FreecodeService.class)
    public FreecodeController initFreecodeController(FreecodeService freecodeService) {

        log.debug("init freecode controller");
        return new FreecodeController(freecodeService);
    }

}
