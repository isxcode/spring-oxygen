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

import com.ispong.oxygen.core.exception.UtilsException;
import com.ispong.oxygen.core.reflect.ReflectUtils;
import com.ispong.oxygen.core.freemarker.FormatUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import static com.ispong.oxygen.core.reflect.ReflectUtils.translateSetName;

/**
 * xml utils
 *
 * @author ispong
 * @version v0.1.0
 */
public class XmlUtils {

    /**
     * xml 通过dom4j实现一层解析
     *
     * @param inputStream 数据流
     * @param clazz       需要解析成的对象类
     * @return 泛型对象
     * @since 2020-01-28
     */
    public static <T> T parseXml(InputStream inputStream, Class<T> clazz) {

        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(inputStream);
            Iterator<?> elementIterator = document.getRootElement().elementIterator();

            T object = ReflectUtils.newInstance(clazz);
            Field[] declaredFields = clazz.getDeclaredFields();

            while (elementIterator.hasNext()) {
                Element element = (Element) elementIterator.next();
                String name = FormatUtils.lowerFirstCase(element.getName());
                for (Field field : declaredFields) {
                    if (field.getName().equals(name)) {
                        switch (field.getType().getName()) {
                            case "java.lang.String":
                                clazz.getDeclaredMethod(translateSetName(field), field.getType()).invoke(object, element.getTextTrim());
                                break;
                            case "java.lang.Integer":
                                clazz.getDeclaredMethod(translateSetName(field), field.getType()).invoke(object, Integer.parseInt(element.getTextTrim()));
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
            return object;
        } catch (DocumentException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new UtilsException("xml 解析失败");
        }
    }

}
