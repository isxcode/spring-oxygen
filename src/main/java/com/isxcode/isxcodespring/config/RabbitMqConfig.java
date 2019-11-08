package com.isxcode.isxcodespring.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitMq 配置中心
 *
 * @author ispong
 * @date 2019-11-08
 * @version v0.1.0
 */
@Configuration
@EnableRabbit
public class RabbitMqConfig {

    /**
     * 开启监听 @RabbitListener
     * 
     * <p>
     * 
     * @since 2019-11-09
     */
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrentConsumers(3);
        factory.setMaxConcurrentConsumers(10);
        return factory;
    }

    /**
     * 声明connectionFactory
     *
     * @author ispong
     * @date 2019-11-08
     * @version v0.1.0
     */
    @Bean
    public ConnectionFactory connectionFactory() {

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("106.15.189.6");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");

        return connectionFactory;
    }

    /**
     * 声明rabbitAdmin
     *
     * @since 2019-10-28
     */
    @Bean
    public RabbitAdmin rabbitAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    /**
     * 声明rabbitTemplate
     *
     * @author ispong
     * @date 2019-11-08
     * @version v0.1.0
     */
    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    /**
     * 声明queue
     *
     * @since 2019-10-28
     */
    @Bean
    Queue queue() {
        Queue queue = new Queue("rabbitQueue", true);
        rabbitAdmin().declareQueue(queue);
        return queue;
    }

    /**
     * 监听行为
     * 
     * @author ispong
     * @date 2019-11-09
     * @version v0.1.0
     */
    @Bean
    public MessageListener exampleListener() {
        return message -> System.out.println("received: " + message);
    }
    
    /**
     * 声明监听行为
     * 
     * @author ispong
     * @date 2019-11-09
     * @version v0.1.0
     */
    @Bean
    public SimpleMessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueueNames("rabbitQueue");
        container.setMessageListener(exampleListener());
        return container;
    }
    
    

    /**
     * 声明topic类型的exchange
     *
     * @since 2019-10-28
     */
    @Bean
    TopicExchange exchange(RabbitAdmin rabbitAdmin) {
        TopicExchange topicExchange = new TopicExchange("rabbitExchange");
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
        Binding binding = BindingBuilder.bind(queueString).to(exchange).with("rabbitKey");
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

}
