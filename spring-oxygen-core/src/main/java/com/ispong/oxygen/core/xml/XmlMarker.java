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
package com.ispong.oxygen.core.xml;

import com.ispong.oxygen.core.exception.OxygenException;
import com.ispong.oxygen.core.reflect.ReflectMarker;
import lombok.extern.slf4j.Slf4j;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;

/**
 * xml utils
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
public class XmlMarker {

    /**
     * 根据inputStream解析xml内容
     *
     * @param inputStream inputStream
     * @param targetClass 目标class
     * @param <T>         T
     * @return target
     * @since 0.0.1
     */
    public static <T extends DefaultHandler> T parseInputStreamXml(InputStream inputStream, Class<T> targetClass) {

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();

            // 反射实例化
            T target = ReflectMarker.newInstance(targetClass);
            saxParser.parse(inputStream, target);
            return target;
        } catch (ParserConfigurationException | SAXException | IOException e) {

            throw new OxygenException("parse inputStream xml fail");
        }
    }

}
