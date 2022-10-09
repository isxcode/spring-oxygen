package com.isxcode.oxygen.core.excel;

import java.lang.annotation.*;

/**
 * excel style annotation
 *
 * @author ispon
 * @since 2019-10-31
 */
@Target(value = {ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ExcelType {

	/** @return cell name */
	String cellName();

	/** @return cell width */
	int cellWidth() default 10000;

	/** @return cell index */
	int cellIndex() default -1;

	/** @return cell color */
	int[] cellColor() default {245, 247, 250};

	/** @return data format */
	String cellDateFormat() default "yyyy/mm/dd";

	/** @return number format */
	String cellDoubleFormat() default "0.00";
}
