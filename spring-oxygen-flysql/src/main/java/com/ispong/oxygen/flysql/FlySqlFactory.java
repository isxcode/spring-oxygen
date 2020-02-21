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

import com.ispong.oxygen.flysql.annotation.ColumnName;
import com.ispong.oxygen.flysql.model.SqlType;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * flysql factory create builder
 *
 * @author ispong
 * @version 0.0.1
 */
public class FlySqlFactory {

    private static JdbcTemplate jdbcTemplate;

    public FlySqlFactory(JdbcTemplate jdbcTemplate) {

        FlySqlFactory.jdbcTemplate = jdbcTemplate;
    }

    public static  <A> Map<String, String> getColumnMap(Class<A> genericType) {

        Field[] declaredFields = genericType.getDeclaredFields();
        Map<String, String> columnMap = new HashMap<>(declaredFields.length);
        for (Field fieldMeta : declaredFields) {
            if (fieldMeta.isAnnotationPresent(ColumnName.class)) {
                columnMap.put(fieldMeta.getName(), fieldMeta.getAnnotation(ColumnName.class).value());
            } else {
                columnMap.put(fieldMeta.getName(), fieldMeta.getName());
            }
        }
        return columnMap;
    }

    /**
     * view select
     *
     * @since 2019-12-23
     */
    public static <A> SqlExecutor<A> viewSql(Class<A> genericType) {

        return new SqlExecutor<>(genericType, jdbcTemplate, SqlType.VIEW_SELECT, getColumnMap(genericType));
    }

    /**
     * select
     *
     * @since 2019-12-23
     */
    public static <A> SqlExecutor<A> selectSql(Class<A> genericType) {

        return new SqlExecutor<>(genericType, jdbcTemplate, SqlType.SELECT, getColumnMap(genericType));
    }

    /**
     * update
     *
     * @since 2019-12-23
     */
    public static <A> SqlExecutor<A> updateSql(Class<A> genericType) {

        return new SqlExecutor<>(genericType, jdbcTemplate, SqlType.UPDATE, getColumnMap(genericType));
    }

    /**
     * delete
     *
     * @since 2019-12-23
     */
    public static <A> SqlExecutor<A> deleteSql(Class<A> genericType) {

        return new SqlExecutor<>(genericType, jdbcTemplate, SqlType.DELETE, getColumnMap(genericType));
    }

    /**
     * insert
     *
     * @since 2019-12-23
     */
    public static <A> SqlExecutor<A> insertSql(Class<A> genericType) {

        return new SqlExecutor<>(genericType, jdbcTemplate, SqlType.SAVE, getColumnMap(genericType));
    }

}
