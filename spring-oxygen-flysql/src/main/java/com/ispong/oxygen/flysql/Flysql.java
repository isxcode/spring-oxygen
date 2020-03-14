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

import com.ispong.oxygen.flysql.enums.SqlType;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

/**
 * flysql初始化器
 * 自动注入时,初始化一个Flysql对象
 *
 * @author ispong
 * @version 0.0.1
 */
public class Flysql {

    private static Map<String, JdbcTemplate> jdbcTemplateMap;

    public Flysql(Map<String, JdbcTemplate> jdbcTemplateMap) {

        Flysql.jdbcTemplateMap = jdbcTemplateMap;
    }

    /**
     * 创建插入builder
     *
     * @param genericType    dataClass
     * @param <A>            FlysqlBuilder
     * @param dataSourceName 数据源名称
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public static <A> FlysqlBuilder<A> insert(Class<A> genericType, String dataSourceName) {

        return new FlysqlBuilder<>(SqlType.INSERT, genericType, jdbcTemplateMap.get(dataSourceName));
    }

    /**
     * 创建插入builder
     *
     * @param genericType dataClass
     * @param <A>         FlysqlBuilder
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public static <A> FlysqlBuilder<A> insert(Class<A> genericType) {

        return insert(genericType, FlysqlConstants.PRIMARY_DATASOURCE_NAME);
    }

    /**
     * 创建删除builder
     *
     * @param genericType    dataClass
     * @param <A>            FlysqlBuilder
     * @param dataSourceName 数据源名称
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public static <A> FlysqlBuilder<A> delete(Class<A> genericType, String dataSourceName) {

        return new FlysqlBuilder<>(SqlType.DELETE, genericType, jdbcTemplateMap.get(dataSourceName));
    }

    /**
     * 创建删除builder
     *
     * @param genericType dataClass
     * @param <A>         FlysqlBuilder
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public static <A> FlysqlBuilder<A> delete(Class<A> genericType) {

        return delete(genericType, FlysqlConstants.PRIMARY_DATASOURCE_NAME);
    }

    /**
     * 创建更新builder
     *
     * @param genericType    dataClass
     * @param <A>            FlysqlBuilder
     * @param dataSourceName 数据源名称
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public static <A> FlysqlBuilder<A> update(Class<A> genericType, String dataSourceName) {

        return new FlysqlBuilder<>(SqlType.UPDATE, genericType, jdbcTemplateMap.get(dataSourceName));
    }

    /**
     * 创建更新builder
     *
     * @param genericType dataClass
     * @param <A>         FlysqlBuilder
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public static <A> FlysqlBuilder<A> update(Class<A> genericType) {

        return update(genericType, FlysqlConstants.PRIMARY_DATASOURCE_NAME);
    }

    /**
     * 创建视图查询builder
     *
     * @param genericType    dataClass
     * @param <A>            FlysqlBuilder
     * @param dataSourceName 数据源名称
     * @param viewName       视图名称
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public static <A> FlysqlBuilder<A> view(Class<A> genericType, String dataSourceName, String viewName) {

        return new FlysqlBuilder<>(SqlType.VIEW, genericType, jdbcTemplateMap.get(dataSourceName), viewName);
    }

    /**
     * 创建视图查询builder
     *
     * @param genericType dataClass
     * @param <A>         FlysqlBuilder
     * @param viewName    视图名称
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public static <A> FlysqlBuilder<A> view(String viewName, Class<A> genericType) {

        return new FlysqlBuilder<>(SqlType.VIEW, genericType, jdbcTemplateMap.get(FlysqlConstants.PRIMARY_DATASOURCE_NAME), viewName);
    }

    /**
     * 创建视图查询builder
     *
     * @param genericType    dataClass
     * @param <A>            FlysqlBuilder
     * @param dataSourceName 数据源名称
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public static <A> FlysqlBuilder<A> view(Class<A> genericType, String dataSourceName) {

        return new FlysqlBuilder<>(SqlType.VIEW, genericType, jdbcTemplateMap.get(dataSourceName));
    }

    /**
     * 创建视图查询builder
     *
     * @param genericType dataClass
     * @param <A>         FlysqlBuilder
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public static <A> FlysqlBuilder<A> view(Class<A> genericType) {

        return view(genericType, FlysqlConstants.PRIMARY_DATASOURCE_NAME);
    }

    /**
     * 创建查询builder
     *
     * @param genericType    dataClass
     * @param <A>            FlysqlBuilder
     * @param dataSourceName 数据源名称
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public static <A> FlysqlBuilder<A> select(Class<A> genericType, String dataSourceName) {

        return new FlysqlBuilder<>(SqlType.SELECT, genericType, jdbcTemplateMap.get(dataSourceName));
    }

    /**
     * 创建查询builder
     *
     * @param genericType dataClass
     * @param <A>         FlysqlBuilder
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public static <A> FlysqlBuilder<A> select(Class<A> genericType) {

        return select(genericType, FlysqlConstants.PRIMARY_DATASOURCE_NAME);
    }

}
