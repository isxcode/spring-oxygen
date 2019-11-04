package com.isxcode.isxcodespring.service.impl;

import com.alibaba.fastjson.JSON;
import com.isxcode.isxcodespring.common.BaseResponse;
import com.isxcode.isxcodespring.utils.HttpClientUtils;
import com.isxcode.isxcodespring.websocket.MyHandler;
import com.isxcode.isxcodespring.websocket.RabbitMqHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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
public class SchedulerImpl {

    @Resource
    private MyHandler myHandler;

    @Autowired
    private RabbitMqHandler rabbitMqHandler;

    /**
     * 登录服务
     *
     * @author ispong
     * @date 2019-11-04
     * @version v0.1.0
     */
    @Scheduled(cron ="* 20 14 * * ?")
    public void checkInService(){

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
        }catch (IOException ex){
            try {
                Thread.sleep(3600000);
                checkInService();
            } catch (InterruptedException e) {
                log.info("睡眠失败");
            }
        }
    }

}
