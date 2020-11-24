/*
 * Copyright [2020] [ispong]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.isxcode.oxygen.core.excel;

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
     * @return 表头
     */
    String cellName() default "表头名称";

    /**
     * @return 表长
     */
    int cellWidth() default 10000;

    /**
     * @return 字段排序
     */
    int cellIndex() default -1;

    /**
     * @return 表头背景颜色
     */
    int[] cellColor() default {245, 247, 250};

    /**
     * @return 日期格式
     */
    String cellDateFormat() default "yyyy/mm/dd";

    /**
     * @return 数字类型格式
     */
    String cellDoubleFormat() default "0.00";
}
