package com.isxcode.oxygen.flysql.core;

import com.isxcode.oxygen.core.exception.OxygenException;
import com.isxcode.oxygen.core.reflect.ReflectConstants;
import com.isxcode.oxygen.flysql.constant.FlysqlConstants;
import com.isxcode.oxygen.flysql.entity.ColumnProperties;
import com.isxcode.oxygen.flysql.entity.SqlCondition;
import com.isxcode.oxygen.flysql.enums.DataBaseType;
import com.isxcode.oxygen.flysql.enums.OrderType;
import com.isxcode.oxygen.flysql.enums.SqlOperateType;
import com.isxcode.oxygen.flysql.utils.FlysqlUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;

/**
 * 将条件构建成条件list对象
 *
 * @author ispong
 * @version v0.1.0
 */
@Slf4j
public abstract class AbstractSqlBuilder<T> implements FlysqlCondition<T> {

	public final List<SqlCondition> sqlConditions = new ArrayList<>();

	public final List<String> sqlOrderByConditions = new ArrayList<>();

	public final Map<String, ColumnProperties> columnsMap;

	public final DataBaseType dataBaseType;

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
			columnNameList.add(getColumnName(columnName) + " " + columnName);
		}
		sqlConditions.add(
				new SqlCondition(SqlOperateType.SELECT, "", Strings.join(columnNameList, ',')));
		return getSelf();
	}

	@Override
	public T setValue(String columnName, String value) {

		sqlConditions.add(
				new SqlCondition(SqlOperateType.SET_VALUE, ":" + columnName, addSingleQuote(value)));
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

		sqlConditions.add(
				new SqlCondition(SqlOperateType.EQ, getColumnName(columnName), addSingleQuote(value)));
		return getSelf();
	}

	@Override
	public T isToday(String columnName) {

		if (DataBaseType.MONGO.equals(dataBaseType)) {
			return getSelf();
		}

		if (DataBaseType.H2.equals(dataBaseType)) {
			sqlConditions.add(new SqlCondition(SqlOperateType.EQ, getColumnName(columnName), "now()"));
			return getSelf();
		}

		sqlConditions.add(
				new SqlCondition(
						SqlOperateType.EQ,
						"date_format(" + getColumnName(columnName) + ",'%Y-%m-%d')",
						"date_format(now(), '%Y-%m-%d')"));
		return getSelf();
	}

	@Override
	public T isNotToday(String columnName) {

		if (DataBaseType.MONGO.equals(dataBaseType)) {
			return getSelf();
		}

		if (DataBaseType.H2.equals(dataBaseType)) {
			sqlConditions.add(new SqlCondition(SqlOperateType.NE, getColumnName(columnName), "now()"));
			return getSelf();
		}

		sqlConditions.add(
				new SqlCondition(
						SqlOperateType.NE,
						"date_format(" + getColumnName(columnName) + ",'%Y-%m-%d')",
						"date_format(now(), '%Y-%m-%d')"));
		return getSelf();
	}

	@Override
	public T isNotDeleted() {

		if (DataBaseType.MONGO.equals(dataBaseType)) {
			return getSelf();
		}

		sqlConditions.add(new SqlCondition(SqlOperateType.EQ, FlysqlConstants.IS_DELETE_COL, 0));
		return getSelf();
	}

	@Override
	public T isDeleted() {

		if (DataBaseType.MONGO.equals(dataBaseType)) {
			return getSelf();
		}

		sqlConditions.add(new SqlCondition(SqlOperateType.EQ, FlysqlConstants.IS_DELETE_COL, 1));
		return getSelf();
	}

	@Override
	public T ne(String columnName, Object value) {

		if (DataBaseType.MONGO.equals(dataBaseType)) {
			sqlConditions.add(new SqlCondition(SqlOperateType.NE, columnName, value));
			return getSelf();
		}

		sqlConditions.add(
				new SqlCondition(SqlOperateType.NE, getColumnName(columnName), addSingleQuote(value)));
		return getSelf();
	}

	@Override
	public T gt(String columnName, Object value) {

		if (DataBaseType.MONGO.equals(dataBaseType)) {
			sqlConditions.add(new SqlCondition(SqlOperateType.GT, columnName, value));
			return getSelf();
		}

		sqlConditions.add(
				new SqlCondition(SqlOperateType.GT, getColumnName(columnName), addSingleQuote(value)));
		return getSelf();
	}

	@Override
	public T gtEq(String columnName, Object value) {

		if (DataBaseType.MONGO.equals(dataBaseType)) {
			sqlConditions.add(new SqlCondition(SqlOperateType.GT_EQ, columnName, value));
			return getSelf();
		}

		sqlConditions.add(
				new SqlCondition(SqlOperateType.GT_EQ, getColumnName(columnName), addSingleQuote(value)));
		return getSelf();
	}

	@Override
	public T lt(String columnName, Object value) {

		if (DataBaseType.MONGO.equals(dataBaseType)) {
			sqlConditions.add(new SqlCondition(SqlOperateType.LT, columnName, value));
			return getSelf();
		}

		sqlConditions.add(
				new SqlCondition(SqlOperateType.LT, getColumnName(columnName), addSingleQuote(value)));
		return getSelf();
	}

	@Override
	public T ltEq(String columnName, Object value) {

		if (DataBaseType.MONGO.equals(dataBaseType)) {
			sqlConditions.add(new SqlCondition(SqlOperateType.LT_EQ, columnName, value));
			return getSelf();
		}

		sqlConditions.add(
				new SqlCondition(SqlOperateType.LT_EQ, getColumnName(columnName), addSingleQuote(value)));
		return getSelf();
	}

	private List<String> parseInValues(Object... values) {

		List<String> inValues = new ArrayList<>();
		Arrays.stream(values)
				.forEach(
						value -> {
							if (value instanceof List) {
								List<String> tmpValues =
										Arrays.stream(values).map(String::valueOf).collect(Collectors.toList());
								tmpValues.forEach(v -> inValues.add(addSingleQuote(v)));
							} else {
								inValues.add(addSingleQuote(value));
							}
						});
		return inValues;
	}

	@Override
	public T in(String columnName, Object... values) {

		if (DataBaseType.MONGO.equals(dataBaseType)) {
			sqlConditions.add(new SqlCondition(SqlOperateType.IN, columnName, Arrays.asList(values)));
			return getSelf();
		}

		List<String> inValues = parseInValues(values);

		if (!inValues.isEmpty()) {
			sqlConditions.add(
					new SqlCondition(
							SqlOperateType.IN,
							getColumnName(columnName),
							"(" + Strings.join(inValues, ',') + ")"));
		}
		return getSelf();
	}

	@Override
	public T notIn(String columnName, Object... values) {

		if (DataBaseType.MONGO.equals(dataBaseType)) {
			sqlConditions.add(new SqlCondition(SqlOperateType.NOT_IN, columnName, Arrays.asList(values)));
			return getSelf();
		}

		List<String> inValues = parseInValues(values);

		if (!inValues.isEmpty()) {
			sqlConditions.add(
					new SqlCondition(
							SqlOperateType.NOT_IN,
							getColumnName(columnName),
							"(" + Strings.join(inValues, ',') + ")"));
		}
		return getSelf();
	}

	@Override
	public T between(String columnName, Object value1, Object value2) {

		sqlConditions.add(
				new SqlCondition(
						SqlOperateType.BETWEEN,
						getColumnName(columnName),
						addSingleQuote(value1) + " and " + addSingleQuote(value2)));
		return getSelf();
	}

	@Override
	public T notBetween(String columnName, Object value1, Object value2) {

		sqlConditions.add(
				new SqlCondition(
						SqlOperateType.NOT_BETWEEN,
						getColumnName(columnName),
						addSingleQuote(value1) + " and " + addSingleQuote(value2)));
		return getSelf();
	}

	@Override
	public T orderBy(String columnName, OrderType orderType) {

		sqlOrderByConditions.add(getColumnName(columnName) + " " + orderType.getOrderType());
		return getSelf();
	}

	@Override
	public T like(String columnName, String value) {

		sqlConditions.add(
				new SqlCondition(
						SqlOperateType.LIKE, getColumnName(columnName), addSingleQuote(("%" + value + "%"))));
		return getSelf();
	}

	@Override
	public T notLike(String columnName, String value) {

		sqlConditions.add(
				new SqlCondition(
						SqlOperateType.NOT_LIKE,
						getColumnName(columnName),
						addSingleQuote(("%" + value + "%"))));
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
				sqlConditions.add(
						new SqlCondition(
								SqlOperateType.UPDATE,
								getColumnName(columnName),
								addSingleQuote(sdf2.format(sdf.parse(String.valueOf(value))))));
				return getSelf();
			} catch (ParseException e) {
				return getSelf();
			}
		}

		sqlConditions.add(
				new SqlCondition(SqlOperateType.UPDATE, getColumnName(columnName), addSingleQuote(value)));
		return getSelf();
	}

	@Override
	public T isNull(String columnName) {

		sqlConditions.add(new SqlCondition(SqlOperateType.IS_NULL, getColumnName(columnName), ""));
		return getSelf();
	}

	@Override
	public T isNotNull(String columnName) {

		sqlConditions.add(new SqlCondition(SqlOperateType.IS_NOT_NULL, getColumnName(columnName), ""));
		return getSelf();
	}

	@Override
	public T sql(String sqlStr) {

		sqlConditions.add(new SqlCondition(SqlOperateType.SQL, sqlStr, ""));
		return this.getSelf();
	}

	@Override
	public T andStart() {

		sqlConditions.add(new SqlCondition(SqlOperateType.AND_START, "", ""));
		return this.getSelf();
	}

	@Override
	public T andEnd() {

		sqlConditions.add(new SqlCondition(SqlOperateType.AND_END, "", ""));
		return this.getSelf();
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
			if (value instanceof String) {
				String val = String.valueOf(value);
				if (val.contains("'")) {
					return "'" + val.replace("'", "''") + "'";
				}
			}
			return "'" + value + "'";
		}
	}

	public String getColumnName(String columnName) {

		ColumnProperties columnProperties = columnsMap.get(columnName);
		if (columnProperties == null) {
			throw new OxygenException("has not column,please check entity exist @ColumnName and value");
		}

		return columnsMap.get(columnName).getName();
	}
}
