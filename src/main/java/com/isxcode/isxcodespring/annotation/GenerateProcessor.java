package com.isxcode.isxcodespring.annotation;

import com.isxcode.isxcodespring.utils.AnnotationUtils;
import com.isxcode.isxcodespring.utils.GeneratorUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Map;

import static com.isxcode.isxcodespring.annotation.GenerateType.SYSTEM;


/**
 * 注解解析器- 解析Generate注解
 *
 * @author ispong
 * @date 2019/10/18
 * @version v0.1.0
 */
@Component
public class GenerateProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        Map<Field, Generate> fieldAnnotation = AnnotationUtils.getFieldAnnotation(bean.getClass(), Generate.class);
        if (fieldAnnotation != null) {
            fieldAnnotation.forEach((key, value) -> {
                try {
                    Method method = bean.getClass().getMethod(AnnotationUtils.translateSetName(key), key.getType());
                    if (method != null) {
                        switch (value.type()) {
                            case SYSTEM:
                                method.invoke(bean, SYSTEM.toString());
                                break;
                            case UUID:
                                method.invoke(bean, GeneratorUtils.generateUuid());
                                break;
                            case DATETIME:
                                method.invoke(bean, LocalDateTime.now());
                                break;
                            default:
                        }
                    }
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
        }
        return bean;
    }

}
