package com.ispong.oxygen.utils.excel;

import java.lang.annotation.*;

/**
 * excel 样式注释
 *
 * @author ispon
 * @since 2019-10-31
 */
@Target(value = {ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ExcelType {

    /**
     * 表头
     */
    String cellName() default "";

    /**
     * 表长
     */
    int cellWidth() default 10;


}
