package com.isxcode.isxcodespring.service.impl;

import com.alibaba.fastjson.JSON;
import com.isxcode.isxcodespring.model.entity.FileEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * rabbit 通知发送websocket
 *
 * 检测到需要发送websocket的信息时,给管道发送websocket
 *
 * @author ispon
 * @since 2019-10-28
 */
@Slf4j
@Component
public class RabbitService {

    private final RabbitTemplate rabbitTemplate;

    private final RabbitAdmin rabbitAdmin;

    public RabbitService(RabbitTemplate rabbitTemplate, RabbitAdmin rabbitAdmin) {

        this.rabbitAdmin = rabbitAdmin;
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * rabbit 简单测试
     *
     * @since 2019-11-07
     */
    @Scheduled(cron = "0 33 15 * * ?")
    public void rabbitDemo() {

        rabbitTemplate.convertAndSend("rabbitQueue", "测试管道传递内容");
        String receive = (String) rabbitTemplate.receiveAndConvert("rabbitQueue");
        System.out.println(receive);
    }

    /**
     * rabbit 简单测试Message对象
     *
     * @since 2019-11-07
     */
    @Scheduled(cron = "0 37 15 * * ?")
    public void rabbitSendDemo() {

        Message message = MessageBuilder.withBody("测试管道传递内容".getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                .setMessageId("123")
                .setHeader("bar", "baz")
                .build();

        rabbitTemplate.send("rabbitQueue",message);
        Object receive = rabbitTemplate.receiveAndConvert("rabbitQueue");
        System.out.println(receive);
    }

    /**
     * rabbit发送对象
     *
     * @since 2019-11-09
     */
    @Scheduled(cron = "0 58 15 * * ?")
    public void rabbitSend() {

        FileEntity fileEntity = new FileEntity();
        fileEntity.setCreateBy("测试rabbit对象");

        Message message = MessageBuilder.withBody(JSON.toJSONString(fileEntity).getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .build();

        rabbitTemplate.send("rabbitQueue", message);
    }

    /**
     * 主动发送推送动作
     *
     * @since 2019-11-09
     */
    public void sendWebsocket(){


    }

    /**
     * rabbit监听对象
     *
     * @since 2019-11-09
     */
    @RabbitListener(queues = "rabbitQueue")
    public void rabbitReceive(Message message) {

        FileEntity fileEntity = JSON.parseObject(new String(message.getBody()), FileEntity.class);
        System.out.print(fileEntity);
    }


}
