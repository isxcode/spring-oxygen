package com.isxcode.oxygen.core.log;

import java.lang.annotation.*;

/**
 * 自定义注解- Log
 *
 * @author ispong
 * @version v0.1.0
 */
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Logs {

    /**
     * 不需要加注解的方法
     */
    String[] exclude() default {};

}
