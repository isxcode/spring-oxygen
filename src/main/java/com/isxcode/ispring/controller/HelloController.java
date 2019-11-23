package com.isxcode.ispring.controller;

import com.isxcode.ispring.annotation.logs.Logs;
import com.isxcode.ispring.common.BaseController;
import com.isxcode.ispring.common.BaseResponse;
import com.isxcode.ispring.model.dto.UserInfoDto;
import com.isxcode.ispring.properties.FreemarkerProperties;
//import io.netty.handler.codec.base64.Base64Encoder;
import com.isxcode.ispring.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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

    private final FreemarkerProperties freeMarkerProperties;

    private final UserRepository userRepository;

//    private final RabbitTemplate rabbitTemplate;

    public HelloController(
            UserRepository userRepository
            , RedisTemplate<String, String> redisTemplate
//            , RabbitTemplate rabbitTemplate
            , FreemarkerProperties freeMarkerProperties
    ) {

//        this.rabbitTemplate = rabbitTemplate;
        this.userRepository = userRepository;
        this.redisTemplate = redisTemplate;
        this.freeMarkerProperties = freeMarkerProperties;
    }

    /**
     * 测试项目启动
     *
     * @since 2019-11-15
     */
    @GetMapping("/test")
    public ResponseEntity<BaseResponse> test() {

//        try {
//            FreemarkerUtils.test();
//        } catch (IOException | TemplateException e) {
//            e.printStackTrace();
//        }

        return successResponse("项目启动成功", userRepository.getCustomUserInfo("asdasf"));

//        log.info(freeMarkerProperties.getTemplatesPath());
//        return successResponse("项目启动成功", Calendar.getInstance().getTime().toString());
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

