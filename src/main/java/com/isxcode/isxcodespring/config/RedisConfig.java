package com.isxcode.isxcodespring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * redis 配置中心  采用Lettuce
 *
 * @author ispong
 * @date 2019-11-06
 * @version v0.1.0
 */
@Configuration
public class RedisConfig {

//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory() {
//
//        return new LettuceConnectionFactory(new RedisStandaloneConfiguration("47.105.33.160", 8088));
//    }
}
