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
package com.isxcode.oxygen.core.reflect;

import com.isxcode.oxygen.core.exception.OxygenException;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 反射Marker
 *
 * @author ispong
 * @since 0.0.1
 */
public class ReflectUtils {

    /**
     * 反射生成实例
     *
     * @param targetClass 目标class
     * @param <T>         T
     * @return T
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
     * 获取对象各个属性
     *
     * @param targetClass 目标class
     * @return List[FieldBody]
     * @since 0.0.1
     */
    public static List<FieldBody> queryFields(Class<?> targetClass) {

        ArrayList<FieldBody> fieldList = new ArrayList<>();

        PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(targetClass);
        for (PropertyDescriptor propertyMeta : propertyDescriptors) {
            if (ClassNameConstants.CLASS.equals(propertyMeta.getName())) {
                continue;
            }

            try {
                FieldBody tempFieldBody = new FieldBody();
                Method readMethod = propertyMeta.getReadMethod();
                if (readMethod != null) {
                    tempFieldBody.setField(readMethod.getDeclaringClass().getDeclaredField(propertyMeta.getName()));
                }
                tempFieldBody.setReadMethod(readMethod);
                tempFieldBody.setWriteMethod(propertyMeta.getWriteMethod());
                tempFieldBody.setClassName(propertyMeta.getPropertyType().getName());
                fieldList.add(tempFieldBody);
            } catch (NoSuchFieldException e) {
                // do nothing
            }
        }

        return fieldList;
    }
}
