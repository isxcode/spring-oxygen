package com.isxcode.oxygen.flysql.annotation;

import java.lang.annotation.*;

/**
 * views
 *
 * @author ispong
 * @since 0.0.1
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface FlysqlViews {

    FlysqlView[] value();
}
