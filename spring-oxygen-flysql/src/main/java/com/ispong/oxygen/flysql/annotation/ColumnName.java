package com.ispong.oxygen.flysql.annotation;

import java.lang.annotation.*;

/**
 * @author ispong
 * @since 0.0.1
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ColumnName {

    String value() default "";
}
