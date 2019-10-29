package com.isxcode.isxcodespring.config;

import com.isxcode.isxcodespring.model.properties.IsxcodeProperties;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class RabbitMqConfig {

    @Resource
    private IsxcodeProperties properties;

    /**
     * 在configuration中需要声明
     *
     * @since 2019-10-28
     */
    @Bean
    RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    /**
     * 声明rabbitMq服务器的queue
     *
     * @since 2019-10-28
     */
    @Bean
    Queue queueString(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(properties.getQueueName(), true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    /**
     * 声明topic类型的exchange
     *
     * @since 2019-10-28
     */
    @Bean
    TopicExchange exchange(RabbitAdmin rabbitAdmin) {
        TopicExchange topicExchange = new TopicExchange(properties.getExchangeName());
        rabbitAdmin.declareExchange(topicExchange);
        return topicExchange;
    }

    /**
     * 必须绑定exchange和queue,spring只会推exchange,
     * 之后的分配是通过绑定的队列去实现的,
     * 具体如何执行是通过routingKey去指定
     *
     * @since 2019-10-28
     */
    @Bean
    Binding bindingExchangeString(Queue queueString, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(queueString).to(exchange).with(properties.getRoutingKey());
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

}
