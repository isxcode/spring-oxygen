///*
// * Copyright [2020] [ispong]
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package com.ispong.oxygen.websockets.websocket;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;
//
///**
// * websocket 配置中心
// *
// * @author ispong
// * @since 0.0.1
// */
//@Configuration
//@EnableWebSocket
//public class WebSocketConfig implements WebSocketConfigurer {
//
//    /**
//     * 配置websocket连接地址,跨域设置,拦截器设置
//     *
//     * @param registry websocket注册对象
//     * @since 0.0.1
//     */
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//
//        registry.addHandler(textSocketHandler(), "/ws/textSocket")
//            .addHandler(binarySocketHandler(), "/ws/binarySocket")
//            .addInterceptors(new WebSocketInterceptor())
//            .setAllowedOrigins("*");
//
//    }
//
//    /**
//     * 注入text websocket处理器
//     *
//     * @return websocket处理器
//     * @since 0.0.1
//     */
//    @Bean
//    public WebSocketHandler textSocketHandler() {
//
//        return new TextSocketHandler();
//    }
//
//    /**
//     * 注入binary websocket处理器
//     *
//     * @return websocket处理器
//     * @since 0.0.1
//     */
//    @Bean
//    public WebSocketHandler binarySocketHandler() {
//
//        return new BinarySocketHandler();
//    }
//
//    /**
//     * 配置websocket服务器设置
//     *
//     * @return 返回webSocket工厂对象
//     * @since 0.0.1
//     */
//    @Bean
//    public ServletServerContainerFactoryBean createWebSocketContainer() {
//
//        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
//        // 最大文本大小
//        container.setMaxTextMessageBufferSize(8192);
//        // 最大二进制大小
//        container.setMaxBinaryMessageBufferSize(8192);
//        // 发送超时时间
//        container.setAsyncSendTimeout(2000L);
//        // 最大等待超时时间
//        container.setMaxSessionIdleTimeout(2000L);
//        return container;
//    }
//
//
//}
