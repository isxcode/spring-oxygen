package com.ispong.oxygen.flysql;

import com.ispong.oxygen.flysql.enums.OrderType;

/**
 * 规定条件
 *
 * @author ispong
 * @since 0.0.1
 */
public interface FlysqlCondition<T> {

    /**
     * select('A','B')
     *
     * @param columnNames columnNames
     * @return self
     * @since 0.0.1
     */
    T select(String... columnNames);

    /**
     * unSelect('T','B')
     *
     * @param columnNames columnNames
     * @return self
     * @since 0.0.1
     */
    T unSelect(String... columnNames);

    /**
     * setVar()
     *
     * @param columnName columnName
     * @param value      value
     * @return self
     * @since 0.0.1
     */
    T setValue(String columnName, String value);

    /**
     * or()
     *
     * @return self
     * @since 0.0.1
     */
    T or();

    /**
     * and()
     *
     * @return self
     * @since 0.0.1
     */
    T and();

    /**
     * eq()
     *
     * @param columnName columnName
     * @param value      value
     * @return self
     * @since 0.0.1
     */
    T eq(String columnName, Object value);

    /**
     * ne()
     *
     * @param columnName columnName
     * @param value      value
     * @return self
     * @since 0.0.1
     */
    T ne(String columnName, Object value);

    /**
     * gt()
     *
     * @param columnName columnName
     * @param value      value
     * @return self
     * @since 0.0.1
     */
    T gt(String columnName, Object value);

    /**
     * gtEq()
     *
     * @param columnName columnName
     * @param value      value
     * @return self
     * @since 0.0.1
     */
    T gtEq(String columnName, Object value);

    /**
     * lt()
     *
     * @param columnName columnName
     * @param value      value
     * @return self
     * @since 0.0.1
     */
    T lt(String columnName, Object value);

    /**
     * ltEq()
     *
     * @param columnName columnName
     * @param value      value
     * @return self
     * @since 0.0.1
     */
    T ltEq(String columnName, Object value);

    /**
     * in()
     *
     * @param columnName columnName
     * @param values     values
     * @return self
     * @since 0.0.1
     */
    T in(String columnName, Object... values);

    /**
     * notIn()
     *
     * @param columnName columnName
     * @param values     values
     * @return self
     * @since 0.0.1
     */
    T notIn(String columnName, Object... values);

    /**
     * between()
     *
     * @param columnName columnName
     * @param value1     value1
     * @param value2     value2
     * @return self
     * @since 0.0.1
     */
    T between(String columnName, Object value1, Object value2);

    /**
     * between()
     *
     * @param columnName columnName
     * @param value1     value1
     * @param value2     value2
     * @return self
     * @since 0.0.1
     */
    T notBetween(String columnName, Object value1, Object value2);

    /**
     * orderBy()
     *
     * @param columnName columnName
     * @param orderType  orderType(desc,asc)
     * @return self
     * @since 0.0.1
     */
    T orderBy(String columnName, OrderType orderType);

    /**
     * like()
     *
     * @param columnName columnName
     * @param value      value
     * @return self
     * @since 0.0.1
     */
    T like(String columnName, String value);

    /**
     * notLike()
     *
     * @param columnName columnName
     * @param value      value
     * @return self
     * @since 0.0.1
     */
    T notLike(String columnName, String value);

    /**
     * limit()
     *
     * @param value limit number
     * @return self
     * @since 0.0.1
     */
    T limit(Integer value);

    /**
     * update()
     *
     * @param columnName columnName
     * @param value      value
     * @return self
     * @since 0.0.1
     */
    T update(String columnName, Object value);

    /**
     * isNull()
     *
     * @param columnName columnName
     * @return self
     * @since 0.0.1
     */
    T isNull(String columnName);

    /**
     * isNull()
     *
     * @param columnName columnName
     * @return self
     * @since 0.0.1
     */
    T isNotNull(String columnName);

}
