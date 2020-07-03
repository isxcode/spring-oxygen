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
package com.ispong.oxygen.flysql.pojo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * sql operate type
 *
 * @author ispong
 * @since 0.0.1
 */
@AllArgsConstructor
public enum SqlOperateType {

    /**
     * select()
     */
    SELECT(""),

    /**
     * eq()
     */
    EQ(" = "),

    /**
     * ne()
     */
    NE(" != "),

    /**
     * setVar()
     */
    SET_VALUE(""),

    /**
     * between()
     */
    BETWEEN(" and "),

    /**
     * lt()
     */
    LT(" < "),

    /**
     * ltEq()
     */
    LT_EQ(" <= "),

    /**
     * gt()
     */
    GT(" > "),

    /**
     * gtEq()
     */
    GT_EQ(" >= "),

    /**
     * orderBy()
     */
    ORDER_BY(""),

    /**
     * like()
     */
    LIKE(" like "),

    /**
     * notIn()
     */
    NOT_IN(" not in "),

    /**
     * in()
     */
    IN(" in "),

    /**
     * or()
     */
    OR(" or "),

    /**
     * and()
     */
    AND(" and "),

    /**
     * limit()
     */
    LIMIT(" limit "),

    /**
     * update()
     */
    UPDATE(" update "),

    /**
     * isNull()
     */
    IS_NULL(" is null "),

    /**
     * isNull()
     */
    IS_NOT_NULL(" is not null "),

    /**
     * notBetween()
     */
    NOT_BETWEEN(" not between "),

    /**
     * notLike()
     */
    NOT_LIKE(" not like "),

    /**
     * sql()
     */
    SQL(""),;

    @Setter
    @Getter
    private String code;
}
