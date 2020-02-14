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
package com.ispong.oxygen.flysql;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * sql拼接抽象类
 *
 * @author ispong
 * @version v0.1.0
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

    public T sql(String sql) {

        sqlStr = new StringBuilder(sql);

        return getSelf();
    }

    /**
     * 不等于
     *
     * @param
     * @return
     * @since 2020-01-16
     */
    public T ne(){

        return getSelf();
    }

    public T eq(){

        return getSelf();
    }

    public T gt(){

        return getSelf();
    }

    public T ge(){

        return getSelf();
    }

    public T lt(){

        return getSelf();
    }

    public T le(){

        return getSelf();
    }

    public T between(){

        return getSelf();
    }

    public T notBetween(){

        return getSelf();
    }

    public T like(){

        return getSelf();
    }

    public T notLike(){

        return getSelf();
    }

    public T isNUll(){

        return getSelf();
    }

    public T isNotNull(){

        return getSelf();
    }

    public T in(){

        return getSelf();
    }

    public T notIn(){

        return getSelf();
    }

    public T orderBy(){

        return getSelf();
    }

    public T setVar(){

        return getSelf();
    }

    public T sql(){

        return getSelf();
    }

    public T select() {

        return getSelf();
    }

    public T unSelect(){

        return getSelf();
    }

    public T update(){

        return getSelf();
    }

    public T count(){

        return getSelf();
    }

    public T distinct(){

        return getSelf();
    }

    public T groupBy() {

        return getSelf();
    }

}
