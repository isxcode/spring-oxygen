package com.isxcode.isxcodespring.utils;

import org.springframework.lang.NonNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 注解解析工具
 *
 * @author ispong
 * @date 2019/10/8
 * @version v0.1.0
 */
public class AnnotationUtils {

    /**
     * 解析field注解
     * 最大字段属性为16个
     * Map<Field, Generate> fieldAnnotation = getFieldAnnotation(annotatedElement.class, Generate.class);
     * fieldAnnotation.forEach((key, value) -> {
     *       System.out.println(key + "：" + value.type());
     * });
     *
     * @param annotatedElement 被注解的对象
     * @param annotationType   注解对象
     * @return Map<Field, < ? extends Annotation>>
     * @since 2019/10/9
     */
    public static <A extends Annotation> Map<Field, A> getFieldAnnotation(Class<?> annotatedElement,@NonNull Class<A> annotationType) {

        Map<Field, A> annotationMap = new HashMap<>(1 << 4);
        Field[] declaredFields = annotatedElement.getDeclaredFields();
        for (Field metaField : declaredFields) {
            if (metaField.isAnnotationPresent(annotationType)) {
                Annotation annotation = metaField.getAnnotation(annotationType);
                annotationMap.put(metaField, (A) annotation);
            }
        }
        if(annotationMap.isEmpty()){
            annotationMap = null;
        }
        return annotationMap;
    }

    /**
     * 转换field的set函数名
     *
     * @param field 属性
     * @return setXXX
     * @since 2019/10/9
     */
    public static String translateSetName(Field field){

        return "set"+field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
    }

}
