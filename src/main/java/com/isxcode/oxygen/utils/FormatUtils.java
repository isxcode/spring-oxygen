package com.isxcode.oxygen.utils;


import com.isxcode.oxygen.autocode.CodeUtils;
import com.isxcode.oxygen.exception.IsxcodeException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Iterator;
import java.util.stream.Collectors;

import static com.isxcode.oxygen.utils.AnnotationUtils.translateSetName;

/**
 * 转换类型工具类
 *
 * @author ispong
 * @date 2019/10/17
 * @version v0.1.0
 */
public class FormatUtils {

    /**
     * 将String类型的数字去除小数点
     *
     * @param doubleStr 有小数点的string
     * @return 去除小数点的string
     * @since 2019-12-09
     */
    public static String parseDoubleStr(String doubleStr) {

        return doubleStr.replaceAll("\\..*", "");
    }

    /**
     * 自动生成随机数
     *
     * @param bit 需要生成几位随机数(最大16位)
     * @since 2019/10/10
     */
    public static String generateNumber(int bit){

        assert bit < 17 : "随机数生成不能超过16位";
        SecureRandom ng = new SecureRandom();
        byte[] randomBytes = new byte[8];
        ng.nextBytes(randomBytes);
        long msb = 0;
        for (byte randomByte : randomBytes) {
            msb = (msb << randomBytes.length) | (randomByte & 0xff);
        }
        return String.valueOf(msb).substring(1, 1 + bit);
    }


    public static void checkEmptyStr(String str, String message) {

        Assert.isTrue(!StringUtils.isEmpty(str), message);
    }


    public static String inputStreamToString(InputStream inputStream) {

        return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines().parallel().collect(Collectors.joining(System.lineSeparator()));
    }

    public static <T> T parseWeChatXml(HttpServletRequest httpServletRequest, Class<T> clazz) {

        SAXReader saxReader = new SAXReader();
        T response = AnnotationUtils.newInstance(clazz);
        Field[] declaredFields = clazz.getDeclaredFields();
        try {
            Document document = saxReader.read(httpServletRequest.getInputStream());
            Iterator<?> elementIterator = document.getRootElement().elementIterator();
            while (elementIterator.hasNext()) {
                Element element = (Element) elementIterator.next();
                String name = CodeUtils.lowerFirstCase(element.getName());
                String textTrim = element.getTextTrim();
                // 反射封装对象
                for (Field field : declaredFields) {
                    if (field.getName().equals(name)) {
                        if ("java.lang.String".equals(field.getType().getName())) {
                            clazz.getDeclaredMethod(translateSetName(field), field.getType()).invoke(response, textTrim);
                        } else if ("java.lang.Integer".equals(field.getType().getName())) {
                            clazz.getDeclaredMethod(translateSetName(field), field.getType()).invoke(response, Integer.parseInt(textTrim));
                        }
                        break;
                    }
                }
            }
            return response;
        } catch (DocumentException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | IOException e) {
            e.printStackTrace();
        }
        throw new IsxcodeException("xml 解析失败");
    }

}
