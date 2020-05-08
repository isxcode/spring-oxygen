package com.ispong.oxygen.websockets.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * websocket service
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
@Service
public class WebSocketService {

    /**
     * 用户socket临时存储
     */
    public static final Map<String, WebSocketBean> SOCKET_MAP = new HashMap<>();

    /**
     * 定时器每一秒推送一次消息
     *
     * @since 0.0.1
     */
//    @SneakyThrows
//    @Scheduled(cron = "0/1 * * * * ?")
//    public void schedulePushMessage() {
//
//        log.debug("每秒推送");
//        if (SOCKET_MAP.containsKey("ispong")) {
//
//            WebSocketBean webSocketBean = SOCKET_MAP.get("ispong");
//            String message = new ObjectMapper().writeValueAsString("hello girl");
//            webSocketBean.getWebSocketSession().sendMessage(new TextMessage(message));
//        }
//    }
}
