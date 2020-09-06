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
package com.ispong.oxygen.flysql.core;

import com.ispong.oxygen.flysql.pojo.constant.FlysqlConstants;
import com.ispong.oxygen.flysql.pojo.entity.FlysqlKey;
import com.ispong.oxygen.flysql.pojo.enums.SqlType;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 初始化一个flysql构造器
 *
 * @author ispong
 * @version 0.0.1
 */
public class Flysql {

    /**
     * 支持多数据源,保存所有的数据源
     */
    private static Map<String, JdbcTemplate> jdbcTemplateMap;

    public Flysql(Map<String, JdbcTemplate> jdbcTemplateMap) {

        Flysql.jdbcTemplateMap = jdbcTemplateMap;
    }

    public static DataSource getDataSource() {
        return jdbcTemplateMap.get(FlysqlConstants.PRIMARY_DATASOURCE_NAME).getDataSource();
    }

    /**
     * insert builder
     *
     * @param targetClass    目标class
     * @param dataSourceName 数据源名称
     * @param <A>            A
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public static <A> FlysqlBuilder<A> insert(String dataSourceName, Class<A> targetClass) {

        FlysqlKey<A> flysqlKey = new FlysqlKey<>(SqlType.INSERT, jdbcTemplateMap.get(dataSourceName), targetClass);
        return new FlysqlBuilder<>(flysqlKey);
    }

    /**
     * inset builder
     *
     * @param targetClass 目标class
     * @param <A>         A
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public static <A> FlysqlBuilder<A> insert(Class<A> targetClass) {

        return insert(FlysqlConstants.PRIMARY_DATASOURCE_NAME, targetClass);
    }

    /**
     * delete builder
     *
     * @param targetClass    目标class
     * @param dataSourceName 数据源名称
     * @param <A>            A
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public static <A> FlysqlBuilder<A> delete(String dataSourceName, Class<A> targetClass) {

        FlysqlKey<A> flysqlKey = new FlysqlKey<>(SqlType.DELETE, jdbcTemplateMap.get(dataSourceName), targetClass);
        return new FlysqlBuilder<>(flysqlKey);
    }

    /**
     * delete builder
     *
     * @param targetClass 目标class
     * @param <A>         A
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public static <A> FlysqlBuilder<A> delete(Class<A> targetClass) {

        return delete(FlysqlConstants.PRIMARY_DATASOURCE_NAME, targetClass);
    }

    /**
     * update builder
     *
     * @param targetClass    目标class
     * @param dataSourceName 数据源名称
     * @param <A>            A
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public static <A> FlysqlBuilder<A> update(String dataSourceName, Class<A> targetClass) {

        FlysqlKey<A> flysqlKey = new FlysqlKey<>(SqlType.UPDATE, jdbcTemplateMap.get(dataSourceName), targetClass);
        return new FlysqlBuilder<>(flysqlKey);
    }

    /**
     * update builder
     *
     * @param targetClass 目标class
     * @param <A>         A
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public static <A> FlysqlBuilder<A> update(Class<A> targetClass) {

        return update(FlysqlConstants.PRIMARY_DATASOURCE_NAME, targetClass);
    }

    /**
     * view builder
     *
     * @param targetClass    目标class
     * @param dataSourceName 数据源名称
     * @param viewName       视图名称
     * @param <A>            A
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public static <A> FlysqlBuilder<A> view(String dataSourceName, String viewName, Class<A> targetClass) {

        FlysqlKey<A> flysqlKey = new FlysqlKey<>(SqlType.VIEW, jdbcTemplateMap.get(dataSourceName), targetClass, viewName);
        return new FlysqlBuilder<>(flysqlKey);
    }

    /**
     * view builder
     *
     * @param targetClass 目标class
     * @param viewName    视图名称
     * @param <A>         A
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public static <A> FlysqlBuilder<A> view(String viewName, Class<A> targetClass) {

        return view(FlysqlConstants.PRIMARY_DATASOURCE_NAME, viewName, targetClass);
    }

    /**
     * select builder
     *
     * @param targetClass    目标class
     * @param dataSourceName 数据源名称
     * @param <A>            A
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public static <A> FlysqlBuilder<A> select(String dataSourceName, Class<A> targetClass) {

        FlysqlKey<A> flysqlKey = new FlysqlKey<>(SqlType.SELECT, jdbcTemplateMap.get(dataSourceName), targetClass);
        return new FlysqlBuilder<>(flysqlKey);
    }

    /**
     * select builder
     *
     * @param targetClass 目标class
     * @param <A>         A
     * @return FlysqlBuilder
     * @since 0.0.1
     */
    public static <A> FlysqlBuilder<A> select(Class<A> targetClass) {

        return select(FlysqlConstants.PRIMARY_DATASOURCE_NAME, targetClass);
    }

}
