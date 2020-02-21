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
package com.ispong.oxygen.flysql.model;

/**
 * operate type
 *
 * @author ispong
 * @since  0.0.1
 */
public enum SqlOperateType {

    /**
     * select()
     */
    SELECT,

    /**
     * eq()
     */
    EQ,
    
    /**
     * ne()
     */
    NE,

    /**
     * setVar()
     */
    SET_VALUE,
    
    /**
     * between()
     */
    BETWEEN,
    
    /**
     * lt()
     */
    LT,

    /**
     * ltEq()
     */
    LT_EQ,

    /**
     * gt()
     */
    GT,

    /**
     * gtEq()
     */
    GT_EQ,

    /**
     * orderBy()
     */
    ORDER_BY,

    /**
     * like()
     */
    LIKE,

    /**
     * notIn()
     */
    NOT_IN,

    /**
     * in()
     */
    IN,

    /**
     * or()
     */
    OR,

    /**
     * and()
     */
    AND,

    /**
     * limit()
     */
    LIMIT,

    UPDATE,
}
