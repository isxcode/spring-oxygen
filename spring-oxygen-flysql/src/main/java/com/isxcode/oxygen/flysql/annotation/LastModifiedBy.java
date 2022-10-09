package com.isxcode.oxygen.flysql.annotation;

import java.lang.annotation.*;

/**
 * sys column last_modified_by
 *
 * @author ispong
 * @since 0.0.1
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface LastModifiedBy {}
