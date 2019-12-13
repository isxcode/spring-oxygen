package com.isxcode.ispring.utils;

import org.springframework.lang.NonNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
                A annotation = metaField.getAnnotation(annotationType);
                annotationMap.put(metaField, annotation);
            }
        }
        if(annotationMap.isEmpty()){
            annotationMap = null;
        }
        return annotationMap;
    }

    /**
     * 获取单例注解
     * 最大字段属性为16个
     *
     * @param annotatedElement 被注解的对象
     * @param annotationType   注解对象
     * @return Map<Field, < ? extends Annotation>>
     * @since 2019/10/9
     */
    public static <A extends Annotation> List<A> getAnnotations(Class<?> annotatedElement, @NonNull Class<A> annotationType) {

        List<A> annotationList = new ArrayList<>(1 << 4);
        Field[] declaredFields = annotatedElement.getDeclaredFields();
        for (Field metaField : declaredFields) {
            if (metaField.isAnnotationPresent(annotationType)) {
                A annotation = metaField.getAnnotation(annotationType);
                annotationList.add(annotation);
            }
        }
        if(annotationList.isEmpty()){
            annotationList = null;
        }
        return annotationList;
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

    /**
     * 检查路径是否存在
     *
     * @param excludes list<String>
     * @param target   目标
     * @return 存在返回true
     * @since 2019-11-11
     */
    public static boolean checkExclude(String[] excludes, String target) {

        for (String metaStr : excludes) {
            if (target.equals(metaStr)) {
                return false;
            }
        }
        return true;

    }

    /**
     * 通过object 反射获取 变量名
     *
     * @param obj 对象
     * @return 变量名
     * @since 2019-12-12
     */
    public static String getObjName(Object obj){
        return obj.getClass().getName();
    }

}
