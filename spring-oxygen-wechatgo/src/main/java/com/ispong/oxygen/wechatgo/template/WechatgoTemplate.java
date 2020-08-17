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
package com.ispong.oxygen.wechatgo.template;

import com.ispong.oxygen.core.http.HttpMarker;
import com.ispong.oxygen.wechatgo.cache.WechatgoTokenCache;
import com.ispong.oxygen.wechatgo.exception.WechatgoException;
import com.ispong.oxygen.wechatgo.pojo.constant.WechatgoConstants;
import com.ispong.oxygen.wechatgo.pojo.properties.WechatgoProperties;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * wechatgo template
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
public class WechatgoTemplate {

    @Resource
    private WechatgoProperties wechatgoProperties;

    @Resource
    private WechatgoTokenCache wechatgoTokenCache;

    /**
     * 发送消息模板
     *
     * @param data data
     * @since 2020-02-04
     */
    public void sendMsgTemplate(String data) {

        try {
            String responseStr = HttpMarker.doPost(wechatgoProperties.getUrl() + "/cgi-bin/message/template/send?access_token=" + wechatgoTokenCache.getToken(WechatgoConstants.ENV), null, data);
            log.debug("send template response string:" + responseStr);
        } catch (IOException e) {
            throw new WechatgoException("send template fail");
        }

    }
}
