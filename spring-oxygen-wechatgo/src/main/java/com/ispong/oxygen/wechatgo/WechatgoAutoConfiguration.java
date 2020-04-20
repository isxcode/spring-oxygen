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
package com.ispong.oxygen.wechatgo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * wechatgo autoConfiguration
 *
 * @author ispong
 * @since  0.0.1
 */
@Slf4j
@EnableCaching
@EnableConfigurationProperties({WechatgoProperties.class})
public class WechatgoAutoConfiguration {

    @Resource
    private WechatgoEventHandler wechatgoEventHandler;

    private final WechatgoProperties wechatgoProperties;

    public WechatgoAutoConfiguration(WechatgoProperties wechatgoProperties) {
        this.wechatgoProperties = wechatgoProperties;
    }

    /**
     * init WechatgoEventHandler
     *
     * @return WechatgoEventHandler
     * @since 0.0.1
     */
    @Bean
    @ConditionalOnMissingBean(WechatgoEventHandler.class)
    public WechatgoEventHandler initWechatgoEventHandler() {

        log.debug("init WechatgoEventHandler");
        return new WechatgoEventHandler() {
        };
    }

    /**
     * init wechatgo banner
     *
     * @since 0.0.1
     */
    @Bean
    @ConditionalOnClass(WechatgoAutoConfiguration.class)
    public void initWechatgoBanner() {

        log.info("welcome to use oxygen-wechatgo");
//        System.out.println("                                                             __          __            ");
//        System.out.println("  ____  _  ____  ______ ____  ____       _      _____  _____/ /_  ____ _/ /_____ _____ ");
//        System.out.println(" / __ \\| |/_/ / / / __ `/ _ \\/ __ \\_____| | /| / / _ \\/ ___/ __ \\/ __ `/ __/ __ `/ __ \\");
//        System.out.println("/ /_/ />  </ /_/ / /_/ /  __/ / / /_____/ |/ |/ /  __/ /__/ / / / /_/ / /_/ /_/ / /_/ /");
//        System.out.println("\\____/_/|_|\\__, /\\__, /\\___/_/ /_/      |__/|__/\\___/\\___/_/ /_/\\__,_/\\__/\\__, /\\____/ ");
//        System.out.println("          /____//____/                                                   /____/        ");
    }

    /**
     * init wechatgo token cache
     *
     * @return WechatgoTokenCache
     * @since  0.0.1
     */
    @Bean
    @ConditionalOnClass(WechatgoAutoConfiguration.class)
    public WechatgoTokenCache initWechatgoTokenCache(){

        log.debug("init wechatgo cache");
        return new WechatgoTokenCache();
    }

    /**
     * init wechatgo service
     *
     * @return WechatgoServiceImpl
     * @since 0.0.1
     */
    @Bean
    @ConditionalOnBean(WechatgoAutoConfiguration.class)
    public WechatgoServiceImpl initWechatgoServiceImpl() {

        log.debug("init wechatgo service");
        return new WechatgoServiceImpl(wechatgoProperties, wechatgoEventHandler);
    }

    /**
     * init wechatgo token
     *
     * @return WeChatTokenGenerator
     * @since 0.0.1
     */
    @Bean
    @ConditionalOnBean(WechatgoService.class)
    public WeChatTokenGenerator initWechatgoToken() {

        log.debug("init wechatgo token");
        return new WeChatTokenGenerator();
    }

    /**
     * init wechatgo controller
     *
     * @return WechatgoController
     * @since 0.0.1
     */
    @Bean
    @ConditionalOnBean(WechatgoService.class)
    public WechatgoController initWechatgoController() {

        log.debug("init wechatgo controller");
        return new WechatgoController();
    }

    /**
     * init wechatgo template
     *
     * @return WechatgoTemplate
     * @since 0.0.1
     */
    @Bean
    @ConditionalOnBean(WechatgoTokenCache.class)
    public WechatgoTemplate initWechatgoTemplate() {

        log.debug("init wechatgo template");
        return new WechatgoTemplate();
    }
}
