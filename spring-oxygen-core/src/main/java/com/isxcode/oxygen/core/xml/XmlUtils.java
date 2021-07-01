package com.isxcode.oxygen.core.xml;

import com.isxcode.oxygen.core.exception.OxygenException;
import com.isxcode.oxygen.core.reflect.ReflectUtils;
import lombok.extern.slf4j.Slf4j;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * xml utils
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
public class XmlUtils {

    /**
     * parse inputSteam content
     *
     * @param inputStream inputStream
     * @param targetClass targetClass
     * @param <T>         T
     * @return target
     * @since 0.0.1
     */
    public static <T extends DefaultHandler> T parseXmlInputStream(InputStream inputStream, Class<T> targetClass) {

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            T target = ReflectUtils.newInstance(targetClass);
            saxParser.parse(inputStream, target);
            return target;
        } catch (ParserConfigurationException | SAXException | IOException e) {

            throw new OxygenException(e.getMessage());
        }
    }


    /**
     * parse string content
     *
     * @param data        stringData
     * @param targetClass targetClass
     * @param <T>         T
     * @return target
     * @since 0.0.1
     */
    public static <T extends DefaultHandler> T parseXmlString(String data, Class<T> targetClass) {

        return parseXmlInputStream(new ByteArrayInputStream(data.getBytes()), targetClass);
    }
}
