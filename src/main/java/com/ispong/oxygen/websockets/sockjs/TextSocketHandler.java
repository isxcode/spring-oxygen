/*
 * Copyright [2020] [ispong]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ispong.oxygen.websockets.sockjs;

import com.ispong.oxygen.websockets.websocket.WebSocketBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import static com.ispong.oxygen.websockets.websocket.WebSocketService.SOCKET_MAP;

/**
 * websocket TextWebSocketHandler 处理方式
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
public class TextSocketHandler extends TextWebSocketHandler {

    /**
     * 构造函数
     *
     * @since 0.0.1
     */
    public TextSocketHandler() {
        super();
    }

    /**
     * 建立握手关系之后的处理
     *
     * @param session websocket session
     * @since 0.0.1
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        log.debug("建立连接");

        // 保存用户websocket session
        WebSocketBean webSocketBean = new WebSocketBean(session);
        String userId = String.valueOf(session.getAttributes().get("userId"));
        SOCKET_MAP.put(userId, webSocketBean);

    }

    /**
     * 数据处理
     *
     * @param session websocket session
     * @param message 消息
     * @since 0.0.1
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

        log.debug("接受到消息" + message.getPayload());
    }

    /**
     * 传输中异常处理
     *
     * @param session   websocket session
     * @param exception 异常
     * @since 0.0.1
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

        log.debug("处理传输异常" + exception.getMessage());
    }

    /**
     * 断开连接处理
     *
     * @param session websocket session
     * @param status  断开状态
     * @since 0.0.1
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

        log.debug("断开连接");

        // 删除用户旧的websocket session
        String userId = String.valueOf(session.getAttributes().get("userId"));
        SOCKET_MAP.remove(userId);

    }

    /**
     * 是否开启大文件拆分推送
     * true开启/false关闭
     *
     * @since 0.0.1
     */
    @Override
    public boolean supportsPartialMessages() {

        return true;
    }

}
