package com.isxcode.oxygen.core.reflect;

import com.isxcode.oxygen.core.exception.OxygenException;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import static java.util.regex.Pattern.compile;

/**
 * reflect utils
 *
 * @author ispong
 * @since 0.0.1
 */
public class ReflectUtils {

    /**
     * reflect create instance
     *
     * @param targetClass targetClass
     * @param <T>         T
     * @return instance
     * @since 0.0.1
     */
    public static <T> T newInstance(Class<T> targetClass) {

        try {
            return targetClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new OxygenException(e.getMessage());
        }
    }

    /**
     * query class fields
     *
     * @param targetClass targetClass
     * @return List[FieldBody]
     * @since 0.0.1
     */
    public static List<FieldBody> queryFields(Class<?> targetClass) {

        ArrayList<FieldBody> fieldBodyList = new ArrayList<>();

        PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(targetClass);
        for (PropertyDescriptor propertyMeta : propertyDescriptors) {

            if (ReflectConstants.CLASS.equals(propertyMeta.getName())) {
                continue;
            }

            try {
                FieldBody.FieldBodyBuilder fieldBodyBuilder = FieldBody.builder();
                Method readMethod = propertyMeta.getReadMethod();
                if (readMethod == null) {
                    continue;
                }
                fieldBodyBuilder.field(readMethod.getDeclaringClass().getDeclaredField(propertyMeta.getName()));
                fieldBodyBuilder.readMethod(readMethod);
                fieldBodyBuilder.writeMethod(propertyMeta.getWriteMethod());
                fieldBodyBuilder.className(propertyMeta.getPropertyType().getName());
                fieldBodyList.add(fieldBodyBuilder.build());
            } catch (NoSuchFieldException e) {
                // do nothing
            }

        }

        return fieldBodyList;

    }

    /**
     * hump translate to under_line
     *
     * @param humpStr humpStr
     * @return String
     * @since 0.0.1
     */
    public static String humpToLine(String humpStr) {

        humpStr = humpStr.substring(0, 1).toLowerCase() + humpStr.substring(1);

        Matcher matcher = compile("[A-Z]").matcher(humpStr);
        StringBuffer lineStrBuff = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(lineStrBuff, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(lineStrBuff);
        return lineStrBuff.toString();
    }
}
