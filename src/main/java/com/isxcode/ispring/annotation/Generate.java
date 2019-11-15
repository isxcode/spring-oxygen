package com.isxcode.ispring.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解-初始化对象中的属性
 *
 * @author ispong
 * @date 2019/10/8
 * @version v0.1.0
 */
@Target(value={ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Generate {

    /**
     * 创建新的对象时，自动初始化32位的uuid
     */
    GenerateType type() default GenerateType.UUID;

}
