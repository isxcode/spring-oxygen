package com.isxcode.oxygen.flysql.annotation;

import java.lang.annotation.*;

/**
 * sys column is_detele
 *
 * @author ispong
 * @since 0.0.1
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface IsDelete {
}
