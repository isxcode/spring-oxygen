//package com.isxcode.isxcodespring.websocket;
//
//import com.isxcode.isxcodespring.model.properties.IsxcodeProperties;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.AmqpAdmin;
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//public class RabbitMqHandler {
//
//    private final AmqpTemplate amqpTemplate;
//
//    private final IsxcodeProperties properties;
//
//    @Autowired
//    public RabbitMqHandler(AmqpTemplate amqpTemplate, IsxcodeProperties properties) {
//        this.properties = properties;
//        this.amqpTemplate = amqpTemplate;
//    }
//
//    public void sendWebsocket(){
//
//        amqpTemplate.convertAndSend(properties.getExchangeName(),properties.getRoutingKey(),"test");
//    }
//
//    @RabbitListener(queues = "isxcode-queue")
//    public void processMessage(String content) {
//        System.out.printf("content"+content);
//    }
//
//}
