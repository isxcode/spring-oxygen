package com.isxcode.ispring.jdbc;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * sql拼接抽象类
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019-12-24
 */
@Component
public abstract class AbstractSqlBuilder<T> {

    /**
     * 储存对象信息
     */
    public final static Map<String, Map<String, String>> BEAN_COLUMNS_MAP = new HashMap<>();


    /**
     * 获取数据库字段
     */
    @Getter
    @Setter
    private Map<String, String> columnNames;

    /**
     * 暂存Sql语句
     */
    @Getter
    @Setter
    private StringBuilder sqlStr;


    // 看看有没有算法
    private Boolean whereFlag = true;

    private Boolean eqFlag = false;

    private Boolean orderByFlag = true;

    private Boolean orderFlag = false;

    /**
     * 获取自己
     *
     * @return 返回自己
     * @since 2019-12-23
     */
    public abstract T getSelf();

    /**
     * 查询的字段
     *
     * @since 3.4.2
     */
    T select(String... columns) {

        for (String metaColumn : columns) {

        }

//        sqlStr = new StringBuilder(sqlStr.toString().replace("*", String.join(",", tempColumns)));

        return getSelf();
    }


    /**
     * 值相等
     *
     * @param column 数据库中的字段名
     * @param value  相等的值
     * @since 2019-12-23
     */
    T eq(String column, String value) {

        if (whereFlag) {
            sqlStr.append(" where ");
            whereFlag = false;
        }
        if (eqFlag) {
            sqlStr.append(" and ");
        }
        eqFlag = true;
        sqlStr.append(columnNames.get(column)).append("=").append("'").append(value).append("'");

        return getSelf();
    }

    /**
     * 值相等
     *
     * @param column 数据库中的字段名
     * @param value  相等的值
     * @since 2019-12-23
     */
    T gt(String column, String value) {

        if (whereFlag) {
            sqlStr.append(" where ");
            whereFlag = false;
        }
        if (eqFlag) {
            sqlStr.append(" and ");
        }
        eqFlag = true;
        sqlStr.append(columnNames.get(column)).append("=").append("'").append(value).append("'");

        return getSelf();
    }

    /**
     * 值相等
     *
     * @param column 数据库中的字段名
     * @param value  相等的值
     * @since 2019-12-23
     */
    T lt(String column, String value) {

        if (whereFlag) {
            sqlStr.append(" where ");
            whereFlag = false;
        }
        if (eqFlag) {
            sqlStr.append(" and ");
        }
        eqFlag = true;
        sqlStr.append(columnNames.get(column)).append("=").append("'").append(value).append("'");

        return getSelf();
    }

    /**
     * 值相等
     *
     * @param column 数据库中的字段名
     * @param value  相等的值
     * @since 2019-12-23
     */
    T between(String column, String value,String value2) {

        if (whereFlag) {
            sqlStr.append(" where ");
            whereFlag = false;
        }
        if (eqFlag) {
            sqlStr.append(" and ");
        }
        eqFlag = true;
        sqlStr.append(columnNames.get(column)).append("=").append("'").append(value).append("'");

        return getSelf();
    }

    /**
     * 值相等
     *
     * @param column 数据库中的字段名
     * @param value  相等的值
     * @since 2019-12-23
     */
    T gtEq(String column, String value) {

        if (whereFlag) {
            sqlStr.append(" where ");
            whereFlag = false;
        }
        if (eqFlag) {
            sqlStr.append(" and ");
        }
        eqFlag = true;
        sqlStr.append(columnNames.get(column)).append("=").append("'").append(value).append("'");

        return getSelf();
    }

    /**
     * 值相等
     *
     * @param column 数据库中的字段名
     * @param value  相等的值
     * @since 2019-12-23
     */
    T ltEq(String column, Object value) {

        if (whereFlag) {
            sqlStr.append(" where ");
            whereFlag = false;
        }
        if (eqFlag) {
            sqlStr.append(" and ");
        }
        eqFlag = true;
        sqlStr.append(columnNames.get(column)).append("=").append("'").append(value).append("'");

        return getSelf();
    }

    /**
     * 符号or
     *
     * @param
     * @return
     * @since 2019-12-23
     */
    public T or() {

        return getSelf();
    }

    /**
     * 符号or
     *
     * @param
     * @return
     * @since 2019-12-23
     */
    public T where() {

        sqlStr.append(" where ");

        return getSelf();
    }

    /**
     * 符号and
     *
     * @param
     * @return
     * @since 2019-12-23
     */
    public T and() {

        return getSelf();
    }

    /**
     * 分组
     *
     * @param
     * @return
     * @since 2019-12-23
     */
    public T groupBy(String columns) {

        return getSelf();
    }

    /**
     * 分组排序
     *
     * @param
     * @return
     * @since 2019-12-23
     */
    public T having(String... conditions) {

        return getSelf();
    }

    /**
     * 排序
     *
     * @param column    类型
     * @param orderType 排序方式
     * @since 2019-12-23
     */
    public T orderBy(String column, String orderType) {

        if (orderByFlag) {
            sqlStr.append(" order by ");
            orderByFlag = false;
        }
        if (orderFlag) {
            sqlStr.append(",");
        }
        orderFlag = true;
        sqlStr.append(columnNames.get(column)).append(" ").append(orderType);

        return getSelf();
    }

    /**
     * 排序
     *
     * @param column    类型
     * @param orderType 排序方式
     * @since 2019-12-23
     */
    public T update(String column, String value) {

        return getSelf();
    }

    /**
     * 个数限制
     *
     * @param value 个数
     * @since 2019-12-23
     */
    public T limit(int value) {

        return getSelf();
    }


}
