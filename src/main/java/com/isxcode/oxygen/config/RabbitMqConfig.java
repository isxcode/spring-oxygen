//package com.isxcode.ispring.config;
//
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.annotation.EnableRabbit;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitAdmin;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * rabbitMq 配置中心
// *
// * @author ispong
// * @date 2019-11-08
// * @version v0.1.0
// */
////@Configuration
//@EnableRabbit
//public class RabbitMqConfig {
//
//    /**
//     * 声明connectionFactory
//     *
//     * @author ispong
//     * @date 2019-11-08
//     * @version v0.1.0
//     */
//    @Bean
//    public ConnectionFactory connectionFactory() {
//
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.setHost("106.15.189.6");
//        connectionFactory.setPort(5672);
//        connectionFactory.setUsername("admin");
//        connectionFactory.setPassword("admin");
//
//        return connectionFactory;
//    }
//
//    /**
//     * 声明rabbitAdmin
//     *
//     * @since 2019-10-28
//     */
//    @Bean
//    public RabbitAdmin rabbitAdmin() {
//
//        return new RabbitAdmin(connectionFactory());
//    }
//
//    /**
//     * 声明rabbitTemplate
//     *
//     * @author ispong
//     * @date 2019-11-08
//     * @version v0.1.0
//     */
//    @Bean
//    public RabbitTemplate rabbitTemplate() {
//
//        return new RabbitTemplate(connectionFactory());
//    }
//
//
//    /**
//     * 声明queue
//     *
//     * @since 2019-10-28
//     */
//    @Bean
//    Queue queue() {
//
//        rabbitAdmin().deleteQueue("rabbitQueue");
//        Queue queue = new Queue("rabbitQueue", true);
//        rabbitAdmin().declareQueue(queue);
//        return queue;
//    }
//
//}
