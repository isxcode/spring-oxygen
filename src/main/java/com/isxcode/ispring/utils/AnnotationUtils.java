package com.isxcode.ispring.utils;

import com.isxcode.ispring.exception.IsxcodeException;
import com.isxcode.ispring.flysql.ColumnName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import static java.beans.Introspector.getBeanInfo;
import static java.util.regex.Pattern.compile;

/**
 * 注解解析工具
 *
 * @author ispong
 * @date 2019/10/8
 * @version v0.1.0
 */
@Slf4j
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
     * 获取class注解
     *
     * @param annotatedElement 被注解的对象
     * @param annotationType   注解对象
     * @return Map<Field, < ? extends Annotation>>
     * @since 2019/10/9
     */
    public static <A extends Annotation> A getAnnotation(Class<?> annotatedElement, @NonNull Class<A> annotationType) {

        if (annotatedElement.isAnnotationPresent(annotationType)) {
            return annotatedElement.getAnnotation(annotationType);
        }
        throw new IsxcodeException("注解不存在");
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

    /**
     * 反射实例化
     *
     * @param
     * @return
     * @since 2019-12-23
     */
    public static <A> A newInstance(Class<A> clazz) {

        try {
            Constructor<A> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new IsxcodeException("对象无法实例化");
        }

    }

    /**
     * 反射实例化 支持注解
     * 因为需要解析注解,所有手动反射获取注解
     *
     * @param map   数据
     * @param clazz 泛型对象
     * @return 返回对象
     * @since 2019-12-23
     */
    @NonNull
    public static <A> A mapToBean(Map<String, Object> map, Class<A> clazz) {

        // 反射本身
        A entity = newInstance(clazz);
        invokeSetMethod(map, entity, clazz);

        // 反射父级
        Class<? super A> superclass = clazz.getSuperclass();
        if (superclass != null && !superclass.isInstance(Object.class)) {
            invokeSetMethod(map, entity, superclass);
        }

        return entity;
    }

    /**
     * 调用set方法
     *
     * @param map    数据
     * @param entity 对象
     * @param clazz  对象class
     * @since 2019-12-25
     */
    public static <T> void invokeSetMethod(Map<String, Object> map, T entity, Class<?> clazz) {

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String fieldName;
            if (field.isAnnotationPresent(ColumnName.class)) {
                fieldName = field.getAnnotation(ColumnName.class).value();
            } else {
                fieldName = humpToLine(field.getName());
            }
            if (map.containsKey(fieldName)) {
                try {
                    if ("java.time.LocalDateTime".equals(field.getType().getName())) {
                        getFieldSetMethod(clazz, field).invoke(entity, DateUtils.strToLocalDateTime(map.get(fieldName).toString()));
                    } else {
                        getFieldSetMethod(clazz, field).invoke(entity, map.get(fieldName));
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    log.info("反射失败");
                    e.printStackTrace();
                }
            }
        }
    }



    /**
     * 小驼峰转下划线
     * 当找到一个大写的时候,替换成_小写
     *
     * @param humpStr 小驼峰内容
     * @return 下划线写法
     * @since 2019-12-24
     */
    private static String humpToLine(String humpStr) {

        Matcher matcher = compile("[A-Z]").matcher(humpStr);
        StringBuffer lineStrBuff = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(lineStrBuff, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(lineStrBuff);
        return lineStrBuff.toString();
    }

    /**
     * 下划线转小驼峰
     * 当找到一个大写的时候,替换成_小写
     *
     * @param lineStr 下滑线内容
     * @return 下划线写法
     * @since 2019-12-24
     */
    public static String lineToHump(String lineStr) {

        lineStr = lineStr.toLowerCase();
        StringBuffer humpStrBuff = new StringBuffer();
        Matcher matcher = compile("_(\\w)").matcher(lineStr);
        while (matcher.find()){
            matcher.appendReplacement(humpStrBuff, matcher.group(1).toUpperCase());
        }
        return matcher.appendTail(humpStrBuff).toString();
    }

    /**
     * 获取Field的set方法
     *
     * @param clazz class类
     * @param field 属性名
     * @return 返回方法
     * @since 2019-12-24
     */
    private static Method getFieldSetMethod(Class<?> clazz, Field field) {

        try {
            return clazz.getDeclaredMethod(translateSetName(field), field.getType());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new IsxcodeException("方法不存在");
        }
    }


    @NonNull
    public static <A> A mapToBean2(Map<String, Object> map, Class<A> clazz) {

        A entity = newInstance(clazz);
        BeanInfo beanInfo = null;
        try {
            beanInfo = getBeanInfo(clazz);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        assert beanInfo != null;
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String propertyName = humpToLine(propertyDescriptor.getName());
            if (map.containsKey(propertyName)) {
                try {
                    propertyDescriptor.getWriteMethod().invoke(entity, map.get(propertyName));
                } catch (Exception e) {
                    System.err.println("The failure of " + propertyName + " assignment");
                }
            }
        }
        return entity;
    }

    public static Class<?> getSuperClassGenericType(final Class<?> clazz, final int index) {
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class<?>) params[index];
    }

}
