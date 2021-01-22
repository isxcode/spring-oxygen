package com.isxcode.oxygen.flysql.response;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface SuccessResponse {

    /**
     * default msg
     * @return default msg
     */
    String value() default "";

    /**
     * msg
     * @return msg
     */
    String msg() default "";
}