package com.isxcode.isxcodespring.service.impl;

import com.isxcode.isxcodespring.websocket.MyHandler;
import com.isxcode.isxcodespring.websocket.RabbitMqHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 定时控制器
 *
 * @author ispon
 * @since 2019-10-28
 */
@Slf4j
@Component
public class SchedulerImpl {

    @Resource
    private MyHandler myHandler;

    @Autowired
    private RabbitMqHandler rabbitMqHandler;

//    @Scheduled(cron ="0/5 * * * * ?")
    public void sendWebsocket(){
        log.info("每分钟发送心跳");
        String msg = "isxcode send message";
        rabbitMqHandler.sendWebsocket();
        myHandler.send(msg);
    }

}
