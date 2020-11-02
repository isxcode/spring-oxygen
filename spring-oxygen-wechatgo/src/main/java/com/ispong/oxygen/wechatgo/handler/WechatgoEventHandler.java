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
package com.ispong.oxygen.wechatgo.handler;

import com.ispong.oxygen.wechatgo.pojo.entity.WeChatEventBody;

/**
 * provide method
 *
 * @author ispong
 * @since 0.0.1
 */
public interface WechatgoEventHandler {

    /**
     * subscribe Event
     *
     * @param weChatEventBody weChatEventBody
     * @return 返回给微信服务器
     * @since 0.0.1
     */
    default String subscribeEvent(WeChatEventBody weChatEventBody) {
        return "success";
    }

    /**
     * unsubscribe Event
     *
     * @param weChatEventBody weChatEventBody
     * @return 返回给微信服务器
     * @since 0.0.1
     */
    default String unsubscribeEvent(WeChatEventBody weChatEventBody) {

        return "success";
    }

    /**
     * send Msg Template Response
     *
     * @param weChatEventBody weChatEventBody
     * @return 返回给微信服务器
     * @since 0.0.1
     */
    default String sendMsgTemplateResponse(WeChatEventBody weChatEventBody) {

        return "success";
    }

    /**
     * send Msg Template Response
     *
     * @param weChatEventBody weChatEventBody
     * @return 返回给微信服务器
     * @since 0.0.1
     */
    default String scanEvent(WeChatEventBody weChatEventBody) {

        return "success";
    }
}
