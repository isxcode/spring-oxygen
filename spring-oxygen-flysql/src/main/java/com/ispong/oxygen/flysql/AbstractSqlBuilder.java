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

import com.ispong.oxygen.flysql.model.SqlCondition;
import com.ispong.oxygen.flysql.model.SqlStatement;

import java.util.Arrays;

/**
 * sql body builder
 *
 * @author ispong
 * @version v0.1.0
 */
public abstract class AbstractSqlBuilder<T> {

    public final SqlStatement sqlStatement = new SqlStatement();

    /**
     * back self
     *
     * @return self
     * @since 0.0.1
     */
    public abstract T getSelf();

    /**
     * select columns
     *
     * @param columnNames columnNames
     * @return self
     * @since 0.0.1
     */
    public T select(String... columnNames) {

        sqlStatement.columnNames = Arrays.asList(columnNames);
        return getSelf();
    }

    /**
     * or
     *
     * @return self
     * @since 0.0.1
     */
    public T or() {

        return getSelf();
    }

    /**
     * and
     *
     * @return self
     * @since 0.0.1
     */
    public T and() {

        return getSelf();
    }

    /**
     * eq
     *
     * @param columnName columnName
     * @param value      value
     * @return self
     * @since 0.0.1
     */
    public T eq(String columnName, String value) {

        sqlStatement.conditionValues.add(new SqlCondition("eq", columnName, value));
        return getSelf();
    }

    T gtEq(String column, String value) {

        return getSelf();
    }

    T ltEq(String column, Object value) {

        return getSelf();
    }

    public T gt(){

        return getSelf();
    }

    public T lt(){

        return getSelf();
    }

    public T setValue(String columnName, String name) {

        sqlStatement.setValues.put(":" + columnName, "'" + name + "'");
        return getSelf();
    }

    public T having(String... conditions) {

        return getSelf();
    }

    public T limit(int value) {

        return getSelf();
    }

    public T ne(){

        return getSelf();
    }

    public T ge(){

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
