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
package com.ispong.oxygen.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

/**
 * websocket config
 * 要求创建webSocket连接 监听握手和断开过程
 * 如何储存用户的websocket的session
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019-11-12
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    /**
     * 注册websocket
     *
     * @since 2019-10-28
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        registry.addHandler(myHandler(), "/ws/websocketHandler")
                .addInterceptors(new HandShakeHandler())
                .setAllowedOrigins("*");

        registry.addHandler(myHandler(), "/ws/myHandler")
                .addInterceptors(new HandShakeHandler())
                .setAllowedOrigins("*")
                .withSockJS()
                .setHeartbeatTime(30000);

    }

    /**
     * websocket 设置
     *
     * @since 2019-10-28
     */
    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {

        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(8192);
        container.setMaxBinaryMessageBufferSize(8192);
        return container;
    }

    @Bean
    public WebSocketHandler myHandler() {

        return new WebSocketHandler();
    }
}
