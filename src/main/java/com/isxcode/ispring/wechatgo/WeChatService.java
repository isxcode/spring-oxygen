package com.isxcode.ispring.wechatgo;

import com.isxcode.ispring.wechatgo.model.WeChatAccessToken;
import org.springframework.stereotype.Service;

/**
 * 微信接口
 *
 * @author ispong
 * @version v0.1.0
 * @date 2020-01-14
 */
public interface WeChatService {

    /**
     * 获取公众号access_token
     *
     * @param env 微信环境
     * @return 获取公众号token体
     * @since 2020-01-14
     */
    WeChatAccessToken getAccessToken(String env);

    /**
     * 微信公众号校验是否是微信发出的请求
     *
     * @param nonce     随机数
     * @param timestamp 时间戳
     * @param signature 签名
     * @param env       微信环境
     * @return true是微信发送的请求
     * @since 2020-01-15
     */
    Boolean checkWeChat(String nonce, String timestamp, String signature, String env);

    /**
     * 微信公众号发送模板方法
     *
     * @param openId     用户openId
     * @param templateId 模板id
     * @param env        微信环境
     * @since 2020-01-15
     */
    void sendTemplate(String openId, String templateId, String env);
}
