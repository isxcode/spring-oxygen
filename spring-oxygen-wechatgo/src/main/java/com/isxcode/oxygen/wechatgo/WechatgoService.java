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
package com.isxcode.oxygen.wechatgo;

import com.isxcode.oxygen.wechatgo.model.WeChatAccessToken;
import com.isxcode.oxygen.wechatgo.model.WeChatEventBody;

/**
 * 微信接口
 *
 * @author ispong
 * @version v0.1.0
 */
public interface WechatgoService {

    /**
     * 获取公众号access_token
     *
     * @return 获取公众号token体
     * @since 2020-01-14
     */
    WeChatAccessToken getAccessToken();

    /**
     * 微信公众号校验是否是微信发出的请求
     *
     * @param nonce     随机数
     * @param timestamp 时间戳
     * @param signature 签名
     * @return true是微信发送的请求
     * @since 2020-01-15
     */
    Boolean checkWeChat(String nonce, String timestamp, String signature);

    /**
     * 微信推送事件
     *
     * @param weChatEventBody 事件体
     * @since 2020-02-04
     */
    void handlerWechatEvent(WeChatEventBody weChatEventBody);

    /**
     * 发送微信模板(正常发送模板)
     *
     * @param openId     接收者openid
     * @param templateId 模板ID
     * @param data       模板数据
     * @since 2020-02-04
     */
    void sendMsgTemplate(String openId, String templateId, String data);

    /**
     * 发送微信模板(带url发送模板)
     *
     * @param openId     接收者openid
     * @param templateId 模板ID
     * @param data       模板数据
     * @param url        模板跳转链接（海外帐号没有跳转能力）
     * @since 2020-02-04
     */
    void sendMsgTemplate(String openId, String templateId, String url, String data);

    /**
     * 发送微信模板(正常发送模板)
     *
     * @param openId     接收者openid
     * @param templateId 模板ID
     * @param data       模板数据
     * @param appId      所需跳转到的小程序appid（该小程序appid必须与发模板消息的公众号是绑定关联关系，暂不支持小游戏）
     * @param pagePath   所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar），要求该小程序已发布，暂不支持小游戏
     * @since 2020-02-04
     */
    void sendMsgTemplate(String openId, String templateId, String appId, String pagePath, String data);

}
