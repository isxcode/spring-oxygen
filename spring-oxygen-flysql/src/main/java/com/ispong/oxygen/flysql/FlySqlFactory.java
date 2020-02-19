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

import com.ispong.oxygen.flysql.annotation.DateBaseType;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Objects;

/**
 * flysql factory create builder
 *
 * @author ispong
 * @version 0.0.1
 */
public class FlySqlFactory {

    public static JdbcTemplate jdbcTemplate;

    public static DateBaseType dataBaseType;

    public FlySqlFactory(JdbcTemplate jdbcTemplate) {

        FlySqlFactory.jdbcTemplate = jdbcTemplate;
        switch (((HikariDataSource) Objects.requireNonNull(jdbcTemplate.getDataSource())).getDriverClassName()) {
            case "com.mysql.cj.jdbc.Driver":
                dataBaseType = DateBaseType.MYSQL;
                break;
            case "oracle.jdbc.OracleDriver":
                dataBaseType = DateBaseType.ORACLE;
                break;
            default:
        }
    }

    /**
     * dateBase view
     *
     * @since 2019-12-23
     */
    public static <A> SqlExecutor<A> viewSql(Class<A> genericType) {

        return new SqlExecutor<>(genericType, FlysqlConstants.SQL_VIEW, jdbcTemplate, dataBaseType);
    }




//    /**
//     * sqlBuilder工厂 生成新的sqlBuilder
//     *
//     * @param genericType 泛型转移/暂存
//     * @since 2019-12-23
//     */
//    public static <A> SqlBuilder<A> selectSql(Class<A> genericType) {
//
//        SqlBuilder<A> generateSql = generateSql(genericType);
//        String tableName = "";
//        if (genericType.isAnnotationPresent(Table.class)) {
//            tableName = genericType.getAnnotation(Table.class).name();
//        }
//        generateSql.setSqlStr(new StringBuilder("select * from " + tableName));
//        return generateSql;
//    }
//
//    /**
//     * 更新sql拼接
//     *
//     * @since 2019-12-23
//     */
//    static <A> SqlBuilder<A> updateSql(Class<A> genericType) {
//
//        SqlBuilder<A> generateSql = generateSql(genericType);
//        if (!genericType.isAnnotationPresent(Table.class)) {
//            throw new FlysqlException("注解数据库名");
//        }
//        generateSql.setSqlStr(new StringBuilder("update " + genericType.getAnnotation(Table.class).name()));
//        return generateSql;
//    }
//
//    /**
//     * 删除sql拼接
//     *
//     * @since 2019-12-24
//     */
//    static <A> SqlBuilder<A> deleteSql(Class<A> genericType) {
//
//        SqlBuilder<A> generateSql = generateSql(genericType);
//        if (!genericType.isAnnotationPresent(Table.class)) {
//            throw new FlysqlException("注解数据库名");
//        }
//        generateSql.setSqlStr(new StringBuilder("delete " + genericType.getAnnotation(Table.class).name()));
//        return generateSql;
//    }
//
//    /**
//     * 插入sql拼接
//     *
//     * @since 2019-12-24
//     */
//    static <A> SqlBuilder<A> insertSql(Class<A> genericType) {
//
//        SqlBuilder<A> generateSql = generateSql(genericType);
//        if (!genericType.isAnnotationPresent(Table.class)) {
//            throw new FlysqlException("注解数据库名");
//        }
//        generateSql.setSqlStr(new StringBuilder("insert into " + genericType.getAnnotation(Table.class).name()));
//        return generateSql;
//    }
//
//    /**
//     * 解析bean对象,将注解中的数据库字段映射到对象
//     *
//     * @param
//     * @return
//     * @since 2019-12-31
//     */
//    static Map<String, String> parseColumns(Class<?> beanClazz) {
//
//        Map<String, String> columnMap = new HashMap<>();
//
//        for (Field metaField : beanClazz.getDeclaredFields()) {
//
//            if (metaField.isAnnotationPresent(ColumnName.class)) {
//                columnMap.put(metaField.getName(), metaField.getAnnotation(ColumnName.class).value());
//            } else {
//                columnMap.put(metaField.getName(), metaField.getName());
//            }
//        }
//
//        for (Field metaField : beanClazz.getSuperclass().getDeclaredFields()) {
//
//            if (metaField.isAnnotationPresent(ColumnName.class)) {
//                columnMap.put(metaField.getName(), metaField.getAnnotation(ColumnName.class).value());
//            } else {
//                columnMap.put(metaField.getName(), metaField.getName());
//            }
//        }
//
//        return columnMap;
//    }

}
