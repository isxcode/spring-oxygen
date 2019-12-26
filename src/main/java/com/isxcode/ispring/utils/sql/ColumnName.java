package com.isxcode.ispring.utils.sql;


import java.lang.annotation.*;

/**
 * @author ispon
 */
@Target(value = {ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ColumnName {

    /**
     * 不需要加注解的方法
     */
    String value() default "";
}


