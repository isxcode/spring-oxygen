package com.isxcode.oxygen.flysql.utils;

import com.isxcode.oxygen.core.reflect.ReflectConstants;
import com.isxcode.oxygen.core.reflect.ReflectUtils;
import com.isxcode.oxygen.flysql.annotation.ColumnName;
import com.isxcode.oxygen.flysql.annotation.TableName;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * flysql utils
 *
 * @author ispong
 * @since 0.0.2
 */
public class FlysqlUtils {

    /**
     * parse class to map
     *
     * @param genericType targetClass
     * @return Map[propertiesName, type]
     * @since 0.0.1
     */
    public static Map<String, String> parseBeanProperties(Class<?> genericType) {

        PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(genericType);
        Map<String, String> columnNameMap = new HashMap<>(propertyDescriptors.length - 1);

        for (PropertyDescriptor propertyMeta : propertyDescriptors) {
            if (!ReflectConstants.CLASS.equals(propertyMeta.getName())) {
                try {
                    Field metaField = propertyMeta.getReadMethod().getDeclaringClass().getDeclaredField(propertyMeta.getName());
                    if (metaField.isAnnotationPresent(ColumnName.class)) {
                        columnNameMap.put(propertyMeta.getName(), metaField.getAnnotation(ColumnName.class).value());
                    } else {
                        columnNameMap.put(propertyMeta.getName(), ReflectUtils.humpToLine(propertyMeta.getName()));
                    }
                } catch (NoSuchFieldException e) {
                    columnNameMap.put(propertyMeta.getName(), ReflectUtils.humpToLine(propertyMeta.getName()));
                }
            }
        }
        return columnNameMap;
    }

    /* get table name
     *
     * @param targetClass targetClass
     * @return tableName
     * @since 0.0.1
     */
    public static String getTableName(Class<?> targetClass) {

        if (targetClass.isAnnotationPresent(TableName.class)) {
            return targetClass.getAnnotation(TableName.class).value();
        }
        return null;
    }
}
