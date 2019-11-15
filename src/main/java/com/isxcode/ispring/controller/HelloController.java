package com.isxcode.ispring.controller;

import com.alibaba.fastjson.JSON;
import com.isxcode.ispring.annotation.Logs;
import com.isxcode.ispring.model.entity.LogEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.UUID;

/**
 * 测试类
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019-11-11
 */
@Slf4j
@Logs
@RestController
@RequestMapping("hello")
public class HelloController {

    private final RedisTemplate<String, String> redisTemplate;

    private final RabbitTemplate rabbitTemplate;

    public HelloController(RedisTemplate<String, String> redisTemplate, RabbitTemplate rabbitTemplate) {

        this.rabbitTemplate = rabbitTemplate;
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(@RequestParam("test") String test) {

        log.info("test"+test);
//        throw new IsxcodeException("异常");
        return new ResponseEntity<>(Calendar.getInstance().getTime().toString(), HttpStatus.OK);
    }

    @GetMapping("/redis")
    public ResponseEntity<String> redis() {

        String uuid = UUID.randomUUID().toString();
        log.info("uuid:" + uuid);
        redisTemplate.opsForValue().set("isxcode", uuid);
        return new ResponseEntity<>(redisTemplate.opsForValue().get("isxcode"), HttpStatus.OK);
    }

    /**
     * rabbit发送对象
     *
     * @since 2019-11-09
     */
    @GetMapping("/rabbit")
    public ResponseEntity<LogEntity> rabbit() {

        LogEntity logEntity = new LogEntity();
        logEntity.setId(UUID.randomUUID().toString());

        Message message = MessageBuilder.withBody(JSON.toJSONString(logEntity).getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .build();

        rabbitTemplate.send("rabbitQueue", message);
        return new ResponseEntity<>(logEntity, HttpStatus.OK);
    }

    /**
     * rabbit监听对象
     *
     * @since 2019-11-09
     */
    @RabbitListener(queues = "rabbitQueue")
    public void rabbitReceive(Message message) {

        log.info("message:" + JSON.parseObject(new String(message.getBody()), LogEntity.class));
    }


}
