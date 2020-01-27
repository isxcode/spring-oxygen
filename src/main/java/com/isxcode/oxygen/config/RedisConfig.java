//package com.isxcode.ispring.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//
///**
// * redis 配置中心  采用Lettuce
// *
// * @author ispong
// * @date 2019-11-06
// * @version v0.1.0
// */
//@Configuration
//public class RedisConfig {
//
//    /**
//     * Lettuce redis 配置中心
//     *
//     * @since 2019-11-12
//     */
//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory() {
//
//        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
//        configuration.setHostName("47.105.33.160");
//        configuration.setPort(8088);
//        configuration.setPassword("isxcode");
//        return new LettuceConnectionFactory(configuration);
//    }
//}
