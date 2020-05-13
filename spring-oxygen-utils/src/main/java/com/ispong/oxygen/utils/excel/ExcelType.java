package com.ispong.oxygen.utils.excel;

import java.awt.Color;
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
    String cellName();

    /**
     * 表长
     */
    int cellWidth() default 10000;

    /**
     * 排序
     */
    int cellIndex() default -1;

    int cellColorR() default 245;

    int cellColorG() default 247;

    int cellColorB() default 250;

    String cellDateFormat() default "m/d/yy";
}
