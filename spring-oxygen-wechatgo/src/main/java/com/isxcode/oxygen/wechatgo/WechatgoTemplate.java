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

import com.isxcode.oxygen.wechatgo.utils.HttpClientUtils;

import javax.annotation.Resource;


/**
 * wechatgo template
 *
 * @author ispong
 * @version v0.1.0
 */
public class WechatgoTemplate {

    @Resource
    private WechatgoProperties wechatgoProperties;

    @Resource
    private WechatgoTokenCache wechatgoTokenCache;

    /**
     * send Msg Template
     *
     * @param data data
     * @since 2020-02-04
     */
    public void sendMsgTemplate(String data) {

        try {
            HttpClientUtils.doPost(wechatgoProperties.getUrl() + "/cgi-bin/message/template/send" + "?access_token=" + wechatgoTokenCache.cacheToken(WechatgoConstants.ENV), data);
        } catch (Exception e) {
            throw new WechatgoException("send template fail");
        }

    }
}
