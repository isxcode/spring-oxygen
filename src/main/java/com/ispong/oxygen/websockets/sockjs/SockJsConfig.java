//package com.ispong.oxygen.websockets.sockjs;
//
//import com.ispong.oxygen.websockets.websocket.TextSocketHandler;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
///**
// * sockJs配置中心
// *
// * @author ispong
// * @since 0.0.1
// */
//@Configuration
//@EnableWebSocket
//public class SockJsConfig implements WebSocketConfigurer {
//
//    /**
//     * 注册配置中心
//     *
//     * @since 0.0.1
//     */
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//
//        registry.addHandler(textSocketHandler(), "/ws/textSocket_js")
//            .withSockJS()
//            .setInterceptors(new WebSocketInterceptor())
//            .setHeartbeatTime(3600L);
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
//
//}
