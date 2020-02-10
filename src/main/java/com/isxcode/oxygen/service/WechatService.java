package com.isxcode.oxygen.service;

import com.isxcode.oxygen.wechatgo.WechatgoEventHandler;
import com.isxcode.oxygen.wechatgo.model.WeChatEventBody;
import lombok.extern.slf4j.Slf4j;

/**
 * 实现接口
 *
 * @author ispong
 * @version v0.1.0
 * @date 2020-02-04
 */
@Slf4j
public class WechatService implements WechatgoEventHandler {

    @Override
    public void subscribeEvent(WeChatEventBody weChatEventBody) {
        System.out.println("用户订阅" + weChatEventBody);
    }

    @Override
    public void unsubscribeEvent(WeChatEventBody weChatEventBody) {
        System.out.println("用户取消订阅" + weChatEventBody);
    }
}
