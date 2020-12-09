package com.isxcode.oxygen.core.reflect;

import com.isxcode.oxygen.core.exception.OxygenException;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

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
}
