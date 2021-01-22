package com.isxcode.oxygen.flysql.annotation;

import java.lang.annotation.*;

/**
 * sys column last_modified_date
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface LastModifiedDate {
}
