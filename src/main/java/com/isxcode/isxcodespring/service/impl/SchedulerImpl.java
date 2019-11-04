package com.isxcode.isxcodespring.service.impl;

import com.alibaba.fastjson.JSON;
import com.isxcode.isxcodespring.common.BaseResponse;
import com.isxcode.isxcodespring.exception.IsxcodeException;
import com.isxcode.isxcodespring.model.entity.FileEntity;
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
    @Scheduled(cron ="* 14 13 * * ?")
    public void checkInService(){

        String tokenUrl = "http://k8s.definesys.com:30600/pluto/Users/userLogin";
        Map<String, String> map = new HashMap<>();
//        String requestJson = "" +
//                "{\n" +
//                "   \"account\":\"215\",\n" +
//                "   \"password\":\"yIV9lwDhmjWnHTzcal7Wmg==\"\n" +
//                "}";
        map.put("account", "215");
        map.put("password", "yIV9lwDhmjWnHTzcal7Wmg==");
        try {
            BaseResponse tokenResponse = HttpClientUtils.doPost(tokenUrl, map, BaseResponse.class);
            Map responseMap = JSON.parseObject(String.valueOf(tokenResponse.getData()), HashMap.class);
            String token = String.valueOf(responseMap.get("token"));
            System.out.println("token"+token);
        }catch (IOException ex){
            throw new IsxcodeException("接口请求失败");
        }
    }

}
