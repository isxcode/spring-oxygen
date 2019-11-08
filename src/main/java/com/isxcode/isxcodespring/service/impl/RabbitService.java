package com.isxcode.isxcodespring.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.isxcode.isxcodespring.common.BaseResponse;
import com.isxcode.isxcodespring.utils.HttpClientUtils;
import com.isxcode.isxcodespring.websocket.MyHandler;
import com.isxcode.isxcodespring.websocket.RabbitMqHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.persistence.criteria.Order;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 定时控制器
 *
 * @author ispon
 * @since 2019-10-28
 */
@Slf4j
@Component
public class RabbitService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Resource
    private MyHandler myHandler;

    @Autowired
    private RabbitMqHandler rabbitMqHandler;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * rabbit 简单测试
     *
     * @since 2019-11-07
     */
//    @Scheduled(cron = "0 46 23 * * ?")
    public void rabbitDemo() {

        rabbitTemplate.convertAndSend("rabbitQueue", "foo");
        String foo = (String) rabbitTemplate.receiveAndConvert("rabbitQueue");
        System.out.println(foo);
    }

    /**
     * rabbit 简单send
     *
     * @since 2019-11-07
     */
    @Scheduled(cron = "0 1 0 * * ?")
    public void rabbitSendDemo() {

        // MessageProperties 决定传递对象类型
        Message message = MessageBuilder.withBody("foo".getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                .setMessageId("123")
                .setHeader("bar", "baz")
                .build();

        rabbitTemplate.send("rabbitQueue",message);
        Object receive = rabbitTemplate.receiveAndConvert("rabbitQueue");
        System.out.println(JSON.toJSON(receive));
    }

    @RabbitListener(
            bindings = @QueueBinding(  value = @Queue(value = "myQueue", durable = "true"),
                                       exchange = @Exchange(value = "auto.exch", ignoreDeclarationExceptions = "true"),
                                       key = "orderRoutingKey")
    )
    public void processOrder(Order order) {

    }


//    @Scheduled(cron = "* 49 23 * * ?")
    public void testRedis(){

        redisTemplate.opsForValue().set("hello", "isxcode");
        Boolean hello = redisTemplate.hasKey("hello");
        String helloValue = redisTemplate.opsForValue().get("hello");
        System.out.println(hello + helloValue);
    }

    /**
     * 测试接受消息
     *
     * @since 2019-11-07
     */
//    @RabbitListener(queues = "rabbitQueue")
//    public void receiveRabbitMq(String content) {
//
//        Message receive = rabbitTemplate.receive("rabbitQueue");
//        System.out.println("receive" + receive);
//        System.out.println("content" + content);
//    }

    /**
     * 登录服务
     *
     * @author ispong
     * @date 2019-11-04
     * @version v0.1.0
     */
    @Scheduled(cron = "* 20 14 * * ?")
    public void checkInService() {

        String tokenUrl = "http://k8s.definesys.com:30600/pluto/Users/userLogin";
        String checkInUrl = "http://k8s.definesys.com:30600/pluto/CheckInHistory/userCheckIn";
        Map<String, String> headers = new HashMap<>(1);
        String requestJson = "" +
                "{\n" +
                "   \"account\":\"215\",\n" +
                "   \"password\":\"yIV9lwDhmjWnHTzcal7Wmg==\"\n" +
                "}";
        try {
            BaseResponse tokenResponse = HttpClientUtils.doPost(tokenUrl, requestJson, headers, BaseResponse.class);
            Map responseMap = JSON.parseObject(String.valueOf(tokenResponse.getData()), HashMap.class);
            headers.put("token", String.valueOf(responseMap.get("token")));
            HttpClientUtils.doPost(checkInUrl, "", headers, BaseResponse.class);
        } catch (IOException ex) {
            try {
                Thread.sleep(3600000);
                checkInService();
            } catch (InterruptedException e) {
                log.info("睡眠失败");
            }
        }
    }

}
