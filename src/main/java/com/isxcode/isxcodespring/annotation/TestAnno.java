package com.isxcode.isxcodespring.annotation;

import java.lang.annotation.*;

/**
 * @author ispon
 */
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface TestAnno {

    String[] exclude() default {};

}
