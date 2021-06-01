package com.isxcode.oxygen.flysql.core;

import com.isxcode.oxygen.core.reflect.ReflectConstants;
import com.isxcode.oxygen.core.reflect.ReflectUtils;
import com.isxcode.oxygen.flysql.entity.ColumnProperties;
import com.isxcode.oxygen.flysql.entity.SqlCondition;
import com.isxcode.oxygen.flysql.enums.DataBaseType;
import com.isxcode.oxygen.flysql.enums.OrderType;
import com.isxcode.oxygen.flysql.enums.SqlOperateType;
import com.isxcode.oxygen.flysql.utils.FlysqlUtils;
import org.apache.logging.log4j.util.Strings;

import java.beans.PropertyDescriptor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 将条件构建成条件list对象
 *
 * @author ispong
 * @version v0.1.0
 */
public abstract class AbstractSqlBuilder<T> implements FlysqlCondition<T> {

    public List<SqlCondition> sqlConditions = new ArrayList<>();

    public Map<String, ColumnProperties> columnsMap;

    public DataBaseType dataBaseType;

    public AbstractSqlBuilder(Class<?> genericType, DataBaseType dataBaseType) {

        this.dataBaseType = dataBaseType;
        this.columnsMap = FlysqlUtils.parseBeanProperties(genericType);
    }

    /**
     * get self
     *
     * @return self[T]
     * @since 0.0.1
     */
    public abstract T getSelf();

    @Override
    public T select(String... columnNames) {

        List<String> columnNameList = new ArrayList<>(columnNames.length);
        for (String columnName : columnNames) {
            columnNameList.add(columnsMap.get(columnName).getName() + " " + columnName);
        }
        sqlConditions.add(new SqlCondition(SqlOperateType.SELECT, "", Strings.join(columnNameList, ',')));
        return getSelf();
    }

    @Override
    public T unSelect(String... columnNames) {

        return getSelf();
    }

    @Override
    public T setValue(String columnName, String value) {

        sqlConditions.add(new SqlCondition(SqlOperateType.SET_VALUE, ":" + columnName, addSingleQuote(value)));
        return getSelf();
    }

    @Override
    public T or() {

        sqlConditions.add(new SqlCondition(SqlOperateType.OR, "", ""));
        return getSelf();
    }

    @Override
    public T and() {

        sqlConditions.add(new SqlCondition(SqlOperateType.AND, "", ""));
        return getSelf();
    }

    @Override
    public T eq(String columnName, Object value) {

        if (DataBaseType.MONGO.equals(dataBaseType)) {
            sqlConditions.add(new SqlCondition(SqlOperateType.EQ, columnName, value));
            return getSelf();
        }

        sqlConditions.add(new SqlCondition(SqlOperateType.EQ, columnsMap.get(columnName).getName(), addSingleQuote(value)));
        return getSelf();
    }

    @Override
    public T ne(String columnName, Object value) {

        if (DataBaseType.MONGO.equals(dataBaseType)) {
            sqlConditions.add(new SqlCondition(SqlOperateType.NE, columnName, value));
            return getSelf();
        }

        sqlConditions.add(new SqlCondition(SqlOperateType.NE, columnsMap.get(columnName).getName(), addSingleQuote(value)));
        return getSelf();
    }

    @Override
    public T gt(String columnName, Object value) {

        if (DataBaseType.MONGO.equals(dataBaseType)) {
            sqlConditions.add(new SqlCondition(SqlOperateType.GT, columnName, value));
            return getSelf();
        }

        sqlConditions.add(new SqlCondition(SqlOperateType.GT, columnsMap.get(columnName).getName(), addSingleQuote(value)));
        return getSelf();
    }

    @Override
    public T gtEq(String columnName, Object value) {

        if (DataBaseType.MONGO.equals(dataBaseType)) {
            sqlConditions.add(new SqlCondition(SqlOperateType.GT_EQ, columnName, value));
            return getSelf();
        }

        sqlConditions.add(new SqlCondition(SqlOperateType.GT_EQ, columnsMap.get(columnName).getName(), addSingleQuote(value)));
        return getSelf();
    }

    @Override
    public T lt(String columnName, Object value) {

        if (DataBaseType.MONGO.equals(dataBaseType)) {
            sqlConditions.add(new SqlCondition(SqlOperateType.LT, columnName, value));
            return getSelf();
        }

        sqlConditions.add(new SqlCondition(SqlOperateType.LT, columnsMap.get(columnName).getName(), addSingleQuote(value)));
        return getSelf();
    }

    @Override
    public T ltEq(String columnName, Object value) {

        if (DataBaseType.MONGO.equals(dataBaseType)) {
            sqlConditions.add(new SqlCondition(SqlOperateType.LT_EQ, columnName, value));
            return getSelf();
        }

        sqlConditions.add(new SqlCondition(SqlOperateType.LT_EQ, columnsMap.get(columnName).getName(), addSingleQuote(value)));
        return getSelf();
    }

    @Override
    public T in(String columnName, Object... values) {

        if (DataBaseType.MONGO.equals(dataBaseType)) {
            sqlConditions.add(new SqlCondition(SqlOperateType.IN, columnName, Arrays.asList(values)));
            return getSelf();
        }

        List<String> inValues = new ArrayList<>();
        Arrays.stream(values).forEach(value -> {
            if (value instanceof List) {
                ((List) value).stream().forEach(v -> inValues.add(addSingleQuote(v)));
            } else {
                inValues.add(addSingleQuote(value));
            }
        });

        sqlConditions.add(new SqlCondition(SqlOperateType.IN, columnsMap.get(columnName).getName(), "(" + Strings.join(inValues, ',') + ")"));
        return getSelf();
    }

    @Override
    public T notIn(String columnName, Object... values) {

        if (DataBaseType.MONGO.equals(dataBaseType)) {
            sqlConditions.add(new SqlCondition(SqlOperateType.NOT_IN, columnName, Arrays.asList(values)));
            return getSelf();
        }

        List<String> inValues = new ArrayList<>();
        Arrays.stream(values).forEach(value -> {
            if (value instanceof List) {
                ((List) value).stream().forEach(v -> inValues.add(addSingleQuote(v)));
            } else {
                inValues.add(addSingleQuote(value));
            }
        });

        sqlConditions.add(new SqlCondition(SqlOperateType.NOT_IN, columnsMap.get(columnName).getName(), "(" + Strings.join(inValues, ',') + ")"));
        return getSelf();
    }

    @Override
    public T between(String columnName, Object value1, Object value2) {

        sqlConditions.add(new SqlCondition(SqlOperateType.BETWEEN, columnsMap.get(columnName).getName(), "(" + addSingleQuote(value1) + " and " + addSingleQuote(value2) + ")"));
        return getSelf();
    }

    @Override
    public T notBetween(String columnName, Object value1, Object value2) {

        sqlConditions.add(new SqlCondition(SqlOperateType.NOT_BETWEEN, columnsMap.get(columnName).getName(), "(" + addSingleQuote(value1) + " and " + addSingleQuote(value2) + ")"));
        return getSelf();
    }

    @Override
    public T orderBy(String columnName, OrderType orderType) {

        sqlConditions.add(new SqlCondition(SqlOperateType.ORDER_BY, columnsMap.get(columnName).getName(), orderType.getOrderType()));
        return getSelf();
    }

    @Override
    public T like(String columnName, String value) {

        sqlConditions.add(new SqlCondition(SqlOperateType.LIKE, columnsMap.get(columnName).getName(), addSingleQuote(("%" + value + "%"))));
        return getSelf();
    }

    @Override
    public T notLike(String columnName, String value) {

        sqlConditions.add(new SqlCondition(SqlOperateType.NOT_LIKE, columnsMap.get(columnName).getName(), addSingleQuote(("%" + value + "%"))));
        return getSelf();
    }

    @Override
    public T limit(Integer value) {

        sqlConditions.add(new SqlCondition(SqlOperateType.LIMIT, "", value));
        return getSelf();
    }

    @Override
    public T update(String columnName, Object value) {

        if (ReflectConstants.DATE.equals(columnsMap.get(columnName).getType())) {
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                sqlConditions.add(new SqlCondition(SqlOperateType.UPDATE, columnsMap.get(columnName).getName(), addSingleQuote(sdf2.format(sdf.parse(String.valueOf(value))))));
                return getSelf();
            } catch (ParseException e) {
                return getSelf();
            }
        }

        sqlConditions.add(new SqlCondition(SqlOperateType.UPDATE, columnsMap.get(columnName).getName(), addSingleQuote(value)));
        return getSelf();
    }

    @Override
    public T isNull(String columnName) {

        sqlConditions.add(new SqlCondition(SqlOperateType.IS_NULL, columnsMap.get(columnName).getName(), ""));
        return getSelf();
    }

    @Override
    public T isNotNull(String columnName) {

        sqlConditions.add(new SqlCondition(SqlOperateType.IS_NOT_NULL, columnsMap.get(columnName).getName(), ""));
        return getSelf();
    }

    @Override
    public T sql(String sqlStr) {

        sqlConditions.add(new SqlCondition(SqlOperateType.SQL, sqlStr, ""));
        return getSelf();
    }

    /**
     * add single quote
     *
     * @param value value
     * @return string
     * @since 0.0.1
     */
    public static String addSingleQuote(Object value) {

        if (value == null) {
            return null;
        } else {
            return "'" + value + "'";
        }
    }
}
