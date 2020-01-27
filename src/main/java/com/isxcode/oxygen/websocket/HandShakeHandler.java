package com.isxcode.oxygen.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * websocket handshake拦截器
 *
 * @author ispong
 * @date 2019-10-28
 * @version v0.1.0
 */
@Slf4j
public class HandShakeHandler implements HandshakeInterceptor {

    /**
     * 握手之前
     *
     * @since 2019-10-28
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {

        log.info("握手前操作");
        return false;
    }

    /**
     * 握手之后
     *
     * @since 2019-10-28
     */
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
