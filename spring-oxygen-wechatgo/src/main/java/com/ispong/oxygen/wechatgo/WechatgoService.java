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
package com.ispong.oxygen.wechatgo;

import com.ispong.oxygen.wechatgo.model.WeChatAccessToken;
import com.ispong.oxygen.wechatgo.model.WeChatEventBody;

/**
 * wechatgo service
 *
 * @author ispong
 * @version v0.1.0
 */
public interface WechatgoService {

    /**
     * get AccessToken
     *
     * @return WeChatAccessToken
     * @since 2020-01-14
     */
    WeChatAccessToken getAccessToken();

    /**
     * check WeChat
     *
     * @param nonce     nonce
     * @param timestamp timestamp
     * @param signature signature
     * @return true/false
     * @since 2020-01-15
     */
    Boolean checkWeChat(String nonce, String timestamp, String signature);

    /**
     * handler Wechat Event
     *
     * @param weChatEventBody weChatEventBody
     * @since 2020-02-04
     */
    void handlerWechatEvent(WeChatEventBody weChatEventBody);

}
