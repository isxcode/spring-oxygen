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
package com.ispong.oxygen.wechatgo.service;

import com.ispong.oxygen.wechatgo.pojo.entity.WeChatAccessToken;
import com.ispong.oxygen.wechatgo.pojo.entity.WeChatEventBody;

/**
 * wechatgo 逻辑处理
 *
 * @author ispong
 * @version v0.1.0
 */
public interface WechatgoService {

    /**
     * 获取有效的微信token
     *
     * @return WeChatAccessToken
     * @since 2020-01-14
     */
    WeChatAccessToken getAccessTokenBody();

    /**
     * 获取有效的微信token
     *
     * @return WeChatAccessToken
     * @since 2020-01-14
     */
    String getAccessToken();

    /**
     * 校验是否是微信调用接口
     *
     * @param nonce     nonce
     * @param timestamp timestamp
     * @param signature signature
     * @return true 是/ flase 不是
     * @since 2020-01-15
     */
    Boolean checkWeChat(String nonce, String timestamp, String signature);

    /**
     * 处理微信回调函数
     *
     * @param weChatEventBody weChatEventBody
     * @since 2020-02-04
     */
    void handlerWechatEvent(WeChatEventBody weChatEventBody);

}
