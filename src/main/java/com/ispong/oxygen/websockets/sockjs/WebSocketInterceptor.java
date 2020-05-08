package com.ispong.oxygen.websockets.sockjs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * websocket 握手拦截处理器
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
public class WebSocketInterceptor implements HandshakeInterceptor {

    /**
     * 握手之前处理
     *
     * @since 0.0.1
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {

        log.debug("握手前处理");

        // 获取请求参数
        String userId = ((ServletServerHttpRequest) request).getServletRequest().getParameter("userId");

        // 放入所有请求数据
        attributes.put("userId", userId);

        // 可以判断是否给予握手 true可以握手/false不可以握手
        return "ispong".equals(userId);
    }

    /**
     * 握手之后处理
     *
     * @since 0.0.1
     */
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

        log.debug("握手后处理");

    }
}
