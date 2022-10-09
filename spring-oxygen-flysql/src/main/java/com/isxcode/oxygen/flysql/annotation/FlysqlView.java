package com.isxcode.oxygen.flysql.annotation;

import com.isxcode.oxygen.flysql.constant.FlysqlConstants;
import java.lang.annotation.*;

/**
 * sql view
 *
 * @author ispong
 * @since 0.0.1
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Repeatable(FlysqlViews.class)
public @interface FlysqlView {

	/**
	 * datasource name
	 *
	 * @return datasource name
	 */
	String datasource();

	/**
	 * view name
	 *
	 * @return view name
	 */
	String name() default FlysqlConstants.PRIMARY_VIEW_NAME;

	/**
	 * view sql
	 *
	 * @return view sql
	 */
	String value();
}
