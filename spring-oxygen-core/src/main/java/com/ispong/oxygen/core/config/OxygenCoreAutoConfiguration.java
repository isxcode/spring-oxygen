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

import com.ispong.oxygen.core.email.EmailUtils;
import com.ispong.oxygen.core.freemarker.FreemarkerUtils;
import com.ispong.oxygen.core.secret.JwtMarker;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailSender;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 * spring-core marker 初始化生成器
 *
 * @author ispong
 * @since 0.0.1
 */
public class OxygenCoreAutoConfiguration {

    /**
     * 初始化EmailThread
     *
     * @return EmailThread
     * @since 0.0.1
     */
    @Bean(name = "emailThread")
    @ConditionalOnBean(OxygenCoreAutoConfiguration.class)
    public ThreadPoolTaskExecutor initEmailThread() {

        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setMaxPoolSize(50);
        taskExecutor.setQueueCapacity(200);
        taskExecutor.setKeepAliveSeconds(60);
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setAwaitTerminationSeconds(60);

        return taskExecutor;
    }

    /**
     * 初始化EmailMarker
     *
     * @param mailSender     mailSender
     * @param mailProperties mailProperties
     * @param emailThread    邮箱线程
     * @return EmailMaker
     * @since 0.0.1
     */
    @Bean
    @ConditionalOnBean(OxygenCoreAutoConfiguration.class)
    @ConditionalOnProperty(prefix = "spring.mail", name = "username", matchIfMissing = false)
    public EmailUtils initEmailMarker(MailSender mailSender,
                                      MailProperties mailProperties,
                                      @Qualifier("emailThread") ThreadPoolTaskExecutor emailThread) {

        return new EmailUtils(mailSender, mailProperties, emailThread);
    }

    /**
     * 初始化FreemarkerMarker
     *
     * @param freeMarkerConfigurer freeMarkerConfigurer
     * @return FreemarkerMarker
     * @since 0.0.1
     */
    @Bean
    @ConditionalOnBean(OxygenCoreAutoConfiguration.class)
    public FreemarkerUtils initFreemarkerMarker(FreeMarkerConfigurer freeMarkerConfigurer) {

        return new FreemarkerUtils(freeMarkerConfigurer);
    }

    /**
     * 初始化JwtMarker
     *
     * @return JwtMarker
     * @since 0.0.1
     */
    @Bean(initMethod = "init")
    @ConditionalOnBean(OxygenCoreAutoConfiguration.class)
    public JwtMarker initJwtMarker() {

        return new JwtMarker();
    }
}
