package com.isxcode.ispring.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置文件加密配置
 *
 * @author ispong
 * @date 2019-12-03
 * @version v0.1.0
 */
@Configuration
public class JasyptConfig {

    /**
     * 加密规则自定义
     *
     * @since 2019-12-03
     */
    @Bean
    public SimpleStringPBEConfig pbeConfig(){

        SimpleStringPBEConfig pbeConfig = new SimpleStringPBEConfig();
        pbeConfig.setPassword("isxcode");
        pbeConfig.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        pbeConfig.setKeyObtentionIterations("1000");
        pbeConfig.setPoolSize("1");
        pbeConfig.setProviderName("SunJCE");
        pbeConfig.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        pbeConfig.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        pbeConfig.setStringOutputType("base64");
        return pbeConfig;
    }

    /**
     * 默认加密配置
     *
     * @since 2019-12-03
     */
    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setConfig(pbeConfig());
        return encryptor;
    }

}
