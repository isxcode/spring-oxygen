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
package com.ispong.oxygen.websockets.websocket;

import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

/**
 * binary socket 处理器
 *
 * @author ispong
 * @since 0.0.1
 */
public class BinarySocketHandler extends BinaryWebSocketHandler {

    /**
     * 构造函数
     *
     * @since 0.0.1
     */
    public BinarySocketHandler() {
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

        super.afterConnectionEstablished(session);
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

        super.handleMessage(session, message);
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

        super.handleTransportError(session, exception);
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

        super.afterConnectionClosed(session, status);
    }

    /**
     * 是否开启大文件拆分推送
     * true开启/false关闭
     *
     * @since 0.0.1
     */
    @Override
    public boolean supportsPartialMessages() {

        return super.supportsPartialMessages();
    }
}
