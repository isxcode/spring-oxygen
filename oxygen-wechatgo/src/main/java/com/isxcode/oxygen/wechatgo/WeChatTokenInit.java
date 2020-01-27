package com.isxcode.oxygen.wechatgo;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.isxcode.oxygen.wechatgo.WeChatProperties.WE_CHAT_ACCESS_TOKENS;
import static java.util.concurrent.Executors.newScheduledThreadPool;

/**
 * 初始化微信的token
 *
 * @author ispong
 * @version v0.1.0
 * @date 2020-01-16
 */
@Component
public class WeChatTokenInit implements InitializingBean {

    private final WeChatServiceImpl weChatService;

    private final WeChatProperties weChatProperties;

    public WeChatTokenInit(WeChatServiceImpl weChatService, WeChatProperties weChatProperties) {

        this.weChatProperties = weChatProperties;
        this.weChatService = weChatService;
    }

    @Override
    public void afterPropertiesSet() {

        ScheduledExecutorService executorService = newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(new Thread(()
                -> weChatProperties.getApps().forEach((k, v)
                -> WE_CHAT_ACCESS_TOKENS.put(k, weChatService.getAccessToken(k).getAccess_token()))),
                0, 1, TimeUnit.HOURS);

    }
}
