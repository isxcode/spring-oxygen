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

import com.isxcode.oxygen.wechatgo.model.WeChatEventBody;

/**
 * provide method
 *
 * @author ispong
 * @since 0.0.1
 */
public interface WechatgoEventHandler {

    /**
     * 订阅事件
     *
     * @param weChatEventBody 事件体
     * @since 2020-02-04
     */
    default void subscribeEvent(WeChatEventBody weChatEventBody) {
        System.out.println("测试进入");
    }

    /**
     * 取消订阅事件
     *
     * @param weChatEventBody 事件体
     * @since 2020-02-04
     */
    default void unsubscribeEvent(WeChatEventBody weChatEventBody) {
        System.out.println("测试进入");
    }
}
