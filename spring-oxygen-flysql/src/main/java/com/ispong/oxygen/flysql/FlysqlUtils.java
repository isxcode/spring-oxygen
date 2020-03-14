package com.ispong.oxygen.flysql;

import com.ispong.oxygen.flysql.annotation.ColumnName;
import com.ispong.oxygen.flysql.annotation.TableName;
import com.ispong.oxygen.flysql.enums.SqlOperateType;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import static java.util.regex.Pattern.compile;

public class FlysqlUtils {

    /**
     * 生成对象中对应的数据库字段
     *
     * @param genericType beanClass
     * @return 对象的字段和数据库字段映射
     * @since 0.0.1
     */
    public static Map<String, String> parseBeanName(Class<?> genericType) {

        PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(genericType);
        Map<String, String> columnNameMap = new HashMap<>(propertyDescriptors.length - 1);
        for (PropertyDescriptor propertyMeta : propertyDescriptors) {
            if (!FlysqlConstants.CLASS.equals(propertyMeta.getName())) {

                try {
                    Field metaField = propertyMeta.getReadMethod().getDeclaringClass().getDeclaredField(propertyMeta.getName());
                    if (metaField.isAnnotationPresent(ColumnName.class)) {
                        columnNameMap.put(propertyMeta.getName(), metaField.getAnnotation(ColumnName.class).value());
                    } else {
                        columnNameMap.put(propertyMeta.getName(), humpToLine(propertyMeta.getName()));
                    }
                } catch (NoSuchFieldException e) {
                    columnNameMap.put(propertyMeta.getName(), humpToLine(propertyMeta.getName()));
                }
            }
        }
        return columnNameMap;
    }

    /**
     * add 'object'
     *
     * @param value object
     * @return 'object'
     * @since 0.0.1
     */
    public static String addSingleQuote(Object value) {

        return "'" + value + "'";
    }


    /**
     * get table name
     *
     * @param genericType 数据对象
     * @return tableName
     * @since 0.0.1
     */
    public static String getTableName(Class<?> genericType) {

        return genericType.getAnnotation(TableName.class).value();
    }

    /**
     * 是否存在操作条件
     *
     * @param sqlCondition   sql条件
     * @param sqlOperateType sql操作类型
     * @return true存在
     * @since 0.0.1
     */
    public static Boolean hasOperateType(SqlCondition sqlCondition, SqlOperateType sqlOperateType) {

        if (sqlCondition == null) {
            return true;
        }
        return sqlCondition.getOperateType().equals(sqlOperateType);
    }

    /**
     * add order by
     *
     * @param sqlConditionTemp sql条件
     * @param sqlStringBuilder sql语句
     * @since 0.0.1
     */
    public static void addOrderBy(SqlCondition sqlConditionTemp, StringBuilder sqlStringBuilder) {

        if (hasOperateType(sqlConditionTemp, SqlOperateType.ORDER_BY)) {
            sqlStringBuilder.append(",");
        } else {
            sqlStringBuilder.append(" order by ");
        }
    }

    /**
     * add where
     *
     * @param sqlConditionTemp sql条件
     * @param sqlStringBuilder sql语句
     * @since 0.0.1
     */
    public static void addWhere(SqlCondition sqlConditionTemp, StringBuilder sqlStringBuilder) {

        if (hasOperateType(sqlConditionTemp, SqlOperateType.UPDATE) || hasOperateType(sqlConditionTemp, SqlOperateType.SELECT) || hasOperateType(sqlConditionTemp, SqlOperateType.SET_VALUE)) {
            sqlStringBuilder.append(" where ");
        } else {
            sqlStringBuilder.append(" and ");
        }
    }

    /**
     * 小驼峰转下划线
     * 当找到一个大写的时候,替换成_小写
     *
     * @param humpStr 小驼峰内容
     * @return 下划线写法
     * @since 2019-12-24
     */
    private static String humpToLine(String humpStr) {

        Matcher matcher = compile("[A-Z]").matcher(humpStr);
        StringBuffer lineStrBuff = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(lineStrBuff, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(lineStrBuff);
        return lineStrBuff.toString();
    }
}
