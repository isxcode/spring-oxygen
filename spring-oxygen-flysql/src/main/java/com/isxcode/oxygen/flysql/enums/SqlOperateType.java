package com.isxcode.oxygen.flysql.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
    BETWEEN(" between "),

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
    SQL(""),

    /**
     * andStart()
     */
    AND_START(" and ( "),

    /**
     * andEnd()
     */
    AND_END(" ) "),;

    @Getter
    private final String code;
}
