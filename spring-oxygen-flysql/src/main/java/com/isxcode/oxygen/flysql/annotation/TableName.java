package com.isxcode.oxygen.flysql.annotation;

import java.lang.annotation.*;

/**
 * config bean from which table
 *
 * @author ispong
 * @since 0.0.1
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TableName {

    /**
     * table name
     * @return table name
     */
    String value();
}
