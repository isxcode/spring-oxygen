package com.isxcode.ispring.controller;

import com.isxcode.ispring.annotation.Logs;
import com.isxcode.ispring.common.BaseController;
import com.isxcode.ispring.common.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;


/**
 * spring项目测试类
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019-11-11
 */
@Slf4j
@Logs
@RestController
@RequestMapping("hello")
public class HelloController extends BaseController {

    private final RedisTemplate<String, String> redisTemplate;

    private final RabbitTemplate rabbitTemplate;

    public HelloController(RedisTemplate<String, String> redisTemplate, RabbitTemplate rabbitTemplate) {

        this.rabbitTemplate = rabbitTemplate;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 测试项目启动
     *
     * @since 2019-11-15
     */
    @GetMapping("/test")
    public ResponseEntity<BaseResponse> test() {

        return successResponse("项目启动成功", Calendar.getInstance().getTime().toString());
    }

//
//
//    @GetMapping("/redis")
//    public ResponseEntity<String> redis() {
//
//        String uuid = UUID.randomUUID().toString();
//        log.info("uuid:" + uuid);
//        redisTemplate.opsForValue().set("isxcode", uuid);
//        return new ResponseEntity<>(redisTemplate.opsForValue().get("isxcode"), HttpStatus.OK);
//    }
//
//    /**
//     * rabbit发送对象
//     *
//     * @since 2019-11-09
//     */
//    @GetMapping("/rabbit")
//    public ResponseEntity<LogEntity> rabbit() {
//
//        LogEntity logEntity = new LogEntity();
//        logEntity.setId(UUID.randomUUID().toString());
//
//        Message message = MessageBuilder.withBody(JSON.toJSONString(logEntity).getBytes())
//                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
//                .build();
//
//        rabbitTemplate.send("rabbitQueue", message);
//        return new ResponseEntity<>(logEntity, HttpStatus.OK);
//    }
//
//    /**
//     * rabbit监听对象
//     *
//     * @since 2019-11-09
//     */
//    @RabbitListener(queues = "rabbitQueue")
//    public void rabbitReceive(Message message) {
//
//        log.info("message:" + JSON.parseObject(new String(message.getBody()), LogEntity.class));
//    }


}

