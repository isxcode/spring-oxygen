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
package com.ispong.oxygen.flysql.annotation;

import com.ispong.oxygen.flysql.FlysqlConstants;
import com.ispong.oxygen.flysql.enums.DateBaseType;

import java.lang.annotation.*;

/**
 * 注解识别子视图
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
     * 指定数据库类型
     * @return 数据库类型
     */
    DateBaseType type() default DateBaseType.DEFAULT;

    /**
     * 指定视图名称
     * @return 视图名称
     */
    String name() default FlysqlConstants.PRIMARY_VIEW_NAME;

    /**
     * 视图内容
     * @return 视图内容
     */
    String value();
}