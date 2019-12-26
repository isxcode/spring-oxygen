package com.isxcode.ispring.websocket;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author ispon
 */
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {

    public static WebSocketSession webSocketSession;

    /**
     * 对外抛出可调用接口
     *
     */
    public void send(Object obj){

        try {
            if( webSocketSession != null){
                TextMessage textMessage = new TextMessage(JSON.toJSONString(obj));
                handleTextMessage(webSocketSession, textMessage);
            }
        } catch (Exception e) {
            log.info("推送文本消息出现异常");
        }
    }

    /**
     * 成功连接之后的操作
     *
     * @since 2019-10-28
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session){

        log.info("建立握手请求");
        webSocketSession = session;
    }

    /**
     * 断开连接之后的操作
     *
     * @since 2019-10-28
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status){

        log.info("断开连接");
        webSocketSession = null;
    }

    /**
     * 推送过程中异常处理
     *
     * @since 2019-10-28
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("推送异常处理");
    }

    /**
     * 给sessionId推送消息
     *
     * @since 2019-10-28
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
    }

    /**
     * 推送二进制消息
     *
     * @since 2019-10-28
     */
    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        super.handleBinaryMessage(session, message);
    }

    /**
     * 推送文本消息
     *
     * @since 2019-10-28
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {

        log.info("推送文本消息");
        // 替换原始的发送动作
        // session.sendMessage(message);


    }

    /**
     * 推送桢消息
     *
     * @since 2019-10-28
     */
    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        super.handlePongMessage(session, message);
    }

    /**
     * 是否开启大文件分割推送
     *
     * @since 2019-10-28
     */
    @Override
    public boolean supportsPartialMessages() {

        return true;
    }

}
