package com.isxcode.ispring.utils.sql;

import com.isxcode.ispring.exception.IsxcodeException;

import javax.persistence.Table;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static com.isxcode.ispring.utils.sql.AbstractSqlBuilder.BEAN_COLUMNS_MAP;

/**
 * 使用工厂模式拼接sql
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019-12-23
 */
public class SqlFactory {

    /**
     * 返回SqlBuilder<T>
     *
     * @since 2019-12-23
     */
    static <A> SqlBuilder<A> selectSql(Class<A> beanClazz) {

        // 创建 SqlBuilder<A>
        SqlBuilder<A> sqlBuilder = new SqlBuilder<>();
        // 保存泛型类型
        sqlBuilder.setBeanClazz(beanClazz);
        // 缓存变量名和数据库列名关系
        String name = beanClazz.getName();
        if (!BEAN_COLUMNS_MAP.containsKey(name)) {
            addColumns(name, beanClazz);
        }
        sqlBuilder.setColumnNames(BEAN_COLUMNS_MAP.get(name));
        // 获取表名
        if (!beanClazz.isAnnotationPresent(Table.class)) {
            throw new IsxcodeException("未注解声明表明");
        }
        // 创建初始sql语句
        sqlBuilder.setSqlStr(new StringBuilder("select * from " + beanClazz.getAnnotation(Table.class).name()));
        return sqlBuilder;
    }

    /**
     * 更新sql拼接
     *
     * @since 2019-12-23
     */
    static SqlBuilder updateSql() {

//        return new SqlBuilder(new StringBuilder("update ${table} "), jdbcTemplate);
        return null;
    }

    /**
     * 删除sql拼接
     *
     * @since 2019-12-24
     */
    static SqlBuilder deleteSql() {

//        return new SqlBuilder(new StringBuilder("delete ${table} "), jdbcTemplate);
        return null;
    }

    /**
     * 插入sql拼接
     *
     * @since 2019-12-24
     */
    static SqlBuilder insertSql() {

//        return new SqlBuilder(new StringBuilder("insert into ${table} "), jdbcTemplate);
        return null;
    }

    static void addColumns(String beanName, Class<?> beanClazz) {

            // 子类
            Field[] fields = beanClazz.getDeclaredFields();
            Map<String, String> columnMap = new HashMap<>();
            for (Field metaField : fields) {
                if (metaField.isAnnotationPresent(ColumnName.class)) {
                    columnMap.put(metaField.getName(), metaField.getAnnotation(ColumnName.class).value());
                } else {
                    columnMap.put(metaField.getName(), metaField.getName());
                }
            }

            // 父类
            fields = beanClazz.getSuperclass().getDeclaredFields();
            for (Field metaField : fields) {
                if (metaField.isAnnotationPresent(ColumnName.class)) {
                    columnMap.put(metaField.getName(), metaField.getAnnotation(ColumnName.class).value());
                } else {
                    columnMap.put(metaField.getName(), metaField.getName());
                }
            }

            BEAN_COLUMNS_MAP.put(beanName, columnMap);


    }
}
