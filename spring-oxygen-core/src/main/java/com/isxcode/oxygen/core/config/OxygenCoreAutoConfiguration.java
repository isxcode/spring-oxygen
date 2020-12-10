package com.isxcode.oxygen.core.config;

import com.isxcode.oxygen.core.email.EmailUtils;
import com.isxcode.oxygen.core.freemarker.FreemarkerUtils;
import com.isxcode.oxygen.core.secret.JwtUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailSender;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 * oxygen-core init auto configuration
 *
 * @author ispong
 * @since 0.0.1
 */
public class OxygenCoreAutoConfiguration {

    /**
     * init email thread
     *
     * @return ThreadPoolTaskExecutor
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
     * init email utils
     *
     * @param mailSender  mailSender
     * @param emailThread 邮箱线程
     * @return EmailMaker
     * @since 0.0.1
     */
    @Bean
    @ConditionalOnBean(OxygenCoreAutoConfiguration.class)
    @ConditionalOnProperty(prefix = "spring.mail", name = "username")
    public EmailUtils initEmailUtils(MailSender mailSender,
                                     @Qualifier("emailThread") ThreadPoolTaskExecutor emailThread) {

        return new EmailUtils(mailSender, emailThread);
    }

    /**
     * freemarker init
     *
     * @param freeMarkerConfigurer freeMarkerConfigurer
     * @return FreemarkerUtils
     * @since 0.0.1
     */
    @Bean
    @ConditionalOnBean(OxygenCoreAutoConfiguration.class)
    public FreemarkerUtils initFreemarkerUtils(FreeMarkerConfigurer freeMarkerConfigurer) {

        return new FreemarkerUtils(freeMarkerConfigurer);
    }

    /**
     * init jwt key
     *
     * @return JwtUtils
     * @since 0.0.1
     */
    @Bean(initMethod = "init")
    @ConditionalOnBean(OxygenCoreAutoConfiguration.class)
    public JwtUtils initJwtUtils() {

        return new JwtUtils();
    }
}
