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
    String cellName() default "";

    /**
     * 表长
     */
    int cellWidth() default 10000;

    /**
     * 字段排序
     */
    int cellIndex() default -1;

    /**
     * 表头背景颜色
     */
    int[] cellColor() default {245, 247, 250};

    /**
     * 日期格式
     */
    String cellDateFormat() default "yyyy/mm/dd";

    /**
     * 数字类型格式
     */
    String cellDoubleFormat() default "0.00";
}
