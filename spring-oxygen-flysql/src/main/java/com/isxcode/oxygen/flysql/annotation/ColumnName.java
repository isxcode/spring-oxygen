package com.isxcode.oxygen.flysql.annotation;

import java.lang.annotation.*;

/**
 * for db column name
 *
 * @author ispong
 * @since 0.0.1
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ColumnName {

	/**
	 * db column name
	 *
	 * @return column name
	 */
	String value();
}
