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

import com.isxcode.oxygen.core.xml.XmlUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * wechat utils
 *
 * @author ispong
 * @version v0.1.0
 * @date 2020-01-28
 */
public class WechatgoUtils {

    /**
     * parse wechat event
     *
     * @param httpServletRequest httpServletRequest
     * @param clazz              response
     * @since 2020-01-30
     */
    public static <T> T parseWeChatXml(HttpServletRequest httpServletRequest, Class<T> clazz) {

        try {
            return XmlUtils.parseXml(httpServletRequest.getInputStream(), clazz);
        } catch (IOException e) {
            throw new WechatgoException("httpServletRequest.getInputStream() is null");
        }

    }


}
