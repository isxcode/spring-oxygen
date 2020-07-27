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
package com.ispong.oxygen.core.config;

import com.ispong.oxygen.core.email.EmailMaker;
import com.ispong.oxygen.core.freemarker.FreemarkerMarker;
import com.ispong.oxygen.core.secret.JwtMarker;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailSender;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 * spring-core marker初始化生成器
 *
 * @author ispong
 * @since 0.0.1
 */
public class OxygenCoreAutoConfiguration {

    /**
     * 初始化邮件静态对象
     *
     * @param mailSender mailSender
     * @return EmailUtils
     * @since 0.0.1
     */
    @Bean
    @ConditionalOnBean(OxygenCoreAutoConfiguration.class)
    @ConditionalOnProperty(prefix = "spring.mail", name = "username", matchIfMissing = false)
    public EmailMaker initEmailMarker(MailSender mailSender, MailProperties mailProperties) {

        return new EmailMaker(mailSender, mailProperties);
    }

    /**
     * 初始化freemarker工具类
     *
     * @param freeMarkerConfigurer freeMarkerConfigurer
     * @return FreemarkerService
     * @since 0.0.1
     */
    @Bean
    @ConditionalOnBean(OxygenCoreAutoConfiguration.class)
    public FreemarkerMarker initFreemarkerMarker(FreeMarkerConfigurer freeMarkerConfigurer) {

        return new FreemarkerMarker(freeMarkerConfigurer);
    }

    /**
     * 初始化 JwtMarker
     *
     * @since 0.0.1
     */
    @Bean(initMethod = "init")
    @ConditionalOnBean(OxygenCoreAutoConfiguration.class)
    public JwtMarker initJwtMarker() {

        return new JwtMarker();
    }
}
