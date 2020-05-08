package com.ispong.oxygen.websockets.stomp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.util.List;

/**
 * stomp配置中心
 *
 * @author ispong
 * @since 0.0.1
 */
@Configuration
@EnableWebSocketMessageBroker
public class StompConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 注册stomp的endPoint
     * stomp是基于websocket之上的
     *
     * @param registry 注册体
     * @since 0.0.1
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        // 设置访问地址 设置跨域
        registry.addEndpoint("/ws/testStomp")
            .setAllowedOrigins("*")
            .withSockJS();

    }

    /**
     * 配置消息broker
     *
     * @param registry 注册体
     * @since 0.0.1
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        // 接受前端推送数据的代理地址前缀 @MessageMapping
        registry.setApplicationDestinationPrefixes("/app");
        // 配置应用的地址
        registry.enableSimpleBroker("/topic", "/queue");

    }

    /**
     *
     *
     * @param
     * @return
     * @since 0.0.1
     */
    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {

    }

    /**
     * 配置客户端内边管道
     *
     * @param registration 注册体
     * @since 0.0.1
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {

    }

    /**
     * 配置客户端外边管道
     *
     * @param registration 注册体
     * @since 0.0.1
     */
    @Override
    public void configureClientOutboundChannel(ChannelRegistration registration) {

    }

    /**
     * 添加请求处理参数
     *
     * @param argumentResolvers 参数处理器
     * @since 0.0.1
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

    }

    /**
     * 添加返回参数处理器
     *
     * @param returnValueHandlers 返回参数处理器
     * @since 0.0.1
     */
    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {

    }

    /**
     * 配置消息天
     *
     * @param
     * @return
     * @since 0.0.1
     */
    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        return false;
    }
}
