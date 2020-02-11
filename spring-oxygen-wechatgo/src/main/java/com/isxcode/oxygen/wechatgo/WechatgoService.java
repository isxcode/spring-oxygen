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
 * wechatgo service
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

}
