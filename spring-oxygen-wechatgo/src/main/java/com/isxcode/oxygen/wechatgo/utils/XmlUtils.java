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
package com.isxcode.oxygen.wechatgo.utils;

import com.isxcode.oxygen.wechatgo.WechatgoException;
import com.isxcode.oxygen.wechatgo.model.WeChatEventBody;
import lombok.extern.slf4j.Slf4j;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;

/**
 * xml utils
 *
 * @author ispong
 * @version v0.1.0
 */
@Slf4j
public class XmlUtils {

    /**
     * parse wechat xml
     *
     * @param inputStream inputStream
     * @return WeChat Event Body
     * @since 2020-01-28
     */
    public static WeChatEventBody parseWechatXml(InputStream inputStream) {

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            WeChatEventBody weChatEventBody = new WeChatEventBody();
            saxParser.parse(inputStream, weChatEventBody);
            return weChatEventBody;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new WechatgoException("parse wechat xml fail");
        }
    }

}
