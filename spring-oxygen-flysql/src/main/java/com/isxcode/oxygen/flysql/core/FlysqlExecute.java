package com.isxcode.oxygen.flysql.core;

import static com.isxcode.oxygen.flysql.enums.SqlOperateType.*;

import com.isxcode.oxygen.core.reflect.FieldBody;
import com.isxcode.oxygen.core.reflect.ReflectConstants;
import com.isxcode.oxygen.core.reflect.ReflectUtils;
import com.isxcode.oxygen.core.snowflake.SnowflakeUtils;
import com.isxcode.oxygen.flysql.annotation.*;
import com.isxcode.oxygen.flysql.common.OxygenHolder;
import com.isxcode.oxygen.flysql.constant.FlysqlConstants;
import com.isxcode.oxygen.flysql.entity.FlysqlKey;
import com.isxcode.oxygen.flysql.entity.FlysqlPage;
import com.isxcode.oxygen.flysql.entity.SqlCondition;
import com.isxcode.oxygen.flysql.enums.SqlOperateType;
import com.isxcode.oxygen.flysql.enums.SqlType;
import com.isxcode.oxygen.flysql.exception.FlysqlException;
import com.isxcode.oxygen.flysql.parse.SqlValue;
import com.isxcode.oxygen.flysql.parse.SqlValueFactory;
import com.isxcode.oxygen.flysql.utils.FlysqlUtils;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * flysql 执行逻辑实现
 *
 * @author ispong
 * @version v0.1.0
 */
@Slf4j
public class FlysqlExecute<A> extends AbstractSqlBuilder<FlysqlExecute<A>>
		implements FlysqlExecutor<A> {

	/** flysql 执行器的钥匙 */
	private final FlysqlKey<A> flysqlKey;

	public FlysqlExecute(FlysqlKey<A> flysqlKey) {

		super(flysqlKey.getTargetClass(), flysqlKey.getDataBaseType());
		this.flysqlKey = flysqlKey;
	}

	@Override
	public FlysqlExecute<A> getSelf() {

		return this;
	}

	// ---------------------------------------- execute sql ----------------------------------------

	@Override
	public A getOne() {

		String sqlString =
				parseSqlConditions(initSelectSql(), sqlConditions, sqlOrderByConditions, "SELECT");

		printSql(sqlString);

		try {
			return flysqlKey
					.getJdbcTemplate()
					.queryForObject(sqlString, new BeanPropertyRowMapper<>(flysqlKey.getTargetClass()));
		} catch (BadSqlGrammarException e) {
			log.error(e.getMessage());
			throw new FlysqlException(e.getCause().getMessage());
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (IncorrectResultSizeDataAccessException e) {
			return flysqlKey
					.getJdbcTemplate()
					.query(sqlString, new BeanPropertyRowMapper<>(flysqlKey.getTargetClass()))
					.get(0);
		}
	}

	@Override
	public List<A> query() {

		try {
			if (flysqlKey.getJdbcTemplate() == null) {
				return flysqlKey
						.getMongoTemplate()
						.find(
								new Query(parseSqlConditions(sqlConditions)),
								flysqlKey.getTargetClass(),
								Objects.requireNonNull(FlysqlUtils.getTableName(flysqlKey.getTargetClass())));
			} else {
				String sqlString =
						parseSqlConditions(initSelectSql(), sqlConditions, sqlOrderByConditions, "SELECT");

				printSql(sqlString);

				// 基础类型返回
				if (flysqlKey.getTargetClass().isInstance("") || flysqlKey.getTargetClass().isInstance(1)) {
					return flysqlKey.getJdbcTemplate().queryForList(sqlString, flysqlKey.getTargetClass());
				} else {
					return flysqlKey
							.getJdbcTemplate()
							.query(sqlString, new BeanPropertyRowMapper<>(flysqlKey.getTargetClass()));
				}
			}
		} catch (BadSqlGrammarException e) {
			log.error(e.getMessage());
			throw new FlysqlException(e.getCause().getMessage());
		}
	}

	@Override
	public FlysqlPage<A> queryPage(Integer page, Integer size) {

		FlysqlPage<A> pageResult = new FlysqlPage<>();
		if (page < 1) {
			pageResult.setTotal(0);
			pageResult.setPage(new ArrayList<>());
			return pageResult;
		}

		String sqlPageString =
				parseSqlConditions(initSelectSql(), sqlConditions, sqlOrderByConditions, "SELECT")
						+ " limit "
						+ (page - 1) * size
						+ " , "
						+ size;
		String sqlCountString =
				parseSqlConditions(initCountSql(), sqlConditions, sqlOrderByConditions, "COUNT");

		printSql(sqlPageString);
		printSql(sqlCountString);

		try {
			pageResult.setPage(
					flysqlKey
							.getJdbcTemplate()
							.query(sqlPageString, new BeanPropertyRowMapper<>(flysqlKey.getTargetClass())));
			pageResult.setTotal(
					flysqlKey.getJdbcTemplate().queryForObject(sqlCountString, Integer.class));
			return pageResult;
		} catch (BadSqlGrammarException e) {
			log.error(e.getMessage());
			throw new FlysqlException(e.getCause().getMessage());
		}
	}

	@Override
	public void doUpdate() {

		sqlConditions.add(
				new SqlCondition(
						UPDATE, FlysqlConstants.LAST_MODIFIED_BY, addSingleQuote(OxygenHolder.getUserUuid())));
		sqlConditions.add(
				new SqlCondition(
						UPDATE,
						FlysqlConstants.LAST_MODIFIED_DATE,
						addSingleQuote(
								DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()))));
		executeUpdate();
	}

	private void executeUpdate() {
		String sqlString =
				parseSqlConditions(initUpdateSql(), sqlConditions, sqlOrderByConditions, "UPADTE");

		printSql(sqlString);

		try {
			flysqlKey.getJdbcTemplate().update(sqlString);
		} catch (BadSqlGrammarException e) {
			log.error(e.getMessage());
			throw new FlysqlException(e.getCause().getMessage());
		}
	}

	@Override
	public void batchSave(List<A> entity) {

		String sqlString = initBatchSaveSql(entity);

		printSql(sqlString);

		try {
			flysqlKey.getJdbcTemplate().execute(sqlString);
		} catch (BadSqlGrammarException e) {
			log.error(e.getMessage());
			throw new FlysqlException(e.getCause().getMessage());
		}
	}

	@Override
	public void save(A entity) {

		String sqlString = initSaveSql(entity);

		printSql(sqlString);

		try {
			if (flysqlKey.getJdbcTemplate() == null) {
				flysqlKey
						.getMongoTemplate()
						.save(
								entity,
								Objects.requireNonNull(FlysqlUtils.getTableName(flysqlKey.getTargetClass())));
			} else {
				flysqlKey.getJdbcTemplate().execute(sqlString);
			}
		} catch (BadSqlGrammarException e) {
			log.error(e.getMessage());
			throw new FlysqlException(e.getCause().getMessage());
		}
	}

	@Override
	public void doDelete() {

		String sqlString =
				parseSqlConditions(initDeleteSql(), sqlConditions, sqlOrderByConditions, "DELETE");

		printSql(sqlString);

		try {
			flysqlKey.getJdbcTemplate().update(sqlString);
		} catch (BadSqlGrammarException e) {
			log.error(e.getMessage());
			throw new FlysqlException(e.getCause().getMessage());
		}
	}

	@Override
	public void doIsDelete() {

		sqlConditions.add(
				new SqlCondition(
						UPDATE, FlysqlConstants.LAST_MODIFIED_BY, addSingleQuote(OxygenHolder.getUserUuid())));
		sqlConditions.add(
				new SqlCondition(
						UPDATE,
						FlysqlConstants.LAST_MODIFIED_DATE,
						addSingleQuote(
								DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()))));
		sqlConditions.add(new SqlCondition(UPDATE, FlysqlConstants.IS_DELETE_COL, "1"));
		executeUpdate();
	}

	@Override
	public Integer count() {

		String sqlString =
				parseSqlConditions(initCountSql(), sqlConditions, sqlOrderByConditions, "COUNT");

		printSql(sqlString);

		try {
			return flysqlKey.getJdbcTemplate().queryForObject(sqlString, Integer.class);
		} catch (BadSqlGrammarException e) {
			log.error(e.getMessage());
			throw new FlysqlException(e.getCause().getMessage());
		}
	}

	// ---------------------------------------- init sql ----------------------------------------

	/**
	 * init count sql
	 *
	 * @return sqlString
	 * @since 0.0.1
	 */
	public String initCountSql() {

		return "select count(1) from " + FlysqlUtils.getTableName(flysqlKey.getTargetClass());
	}

	/**
	 * init delete sql
	 *
	 * @return sqlString
	 * @since 0.0.1
	 */
	public String initDeleteSql() {

		return "delete from " + FlysqlUtils.getTableName(flysqlKey.getTargetClass());
	}

	/**
	 * init update sql
	 *
	 * @return sqlString
	 * @since 0.0.1
	 */
	public String initUpdateSql() {

		StringBuilder sqlStringBuilder =
				new StringBuilder(
						"update " + FlysqlUtils.getTableName(flysqlKey.getTargetClass()) + " set ");

		List<String> updateSetList = new ArrayList<>();
		for (SqlCondition sqlConditionMeta : sqlConditions) {

			if (sqlConditionMeta.getOperateType().equals(UPDATE)) {
				Object value = sqlConditionMeta.getValue();
				if (value == null) {
					updateSetList.add(sqlConditionMeta.getColumnName() + " = null");
				} else {
					updateSetList.add(sqlConditionMeta.getColumnName() + " = " + value);
				}
			}
		}
		return sqlStringBuilder.append(Strings.join(updateSetList, ',')).toString();
	}

	/**
	 * init select sql
	 *
	 * @return sqlString
	 * @since 0.0.1
	 */
	public String initSelectSql() {

		if (flysqlKey.getSqlType().equals(SqlType.VIEW)) {

			// duplicate view
			if (flysqlKey.getTargetClass().isAnnotationPresent(FlysqlViews.class)) {

				FlysqlView[] flysqlViews =
						flysqlKey.getTargetClass().getAnnotation(FlysqlViews.class).value();
				for (FlysqlView metaFlysqlView : flysqlViews) {
					if (flysqlKey.getViewSqlName().equals(metaFlysqlView.name())) {
						return " select "
								+ FlysqlConstants.SELECT_REPLACE_CONTENT
								+ " from ( "
								+ metaFlysqlView.value()
								+ " ) flysql ";
					}
				}
			}

			// singel view
			if (flysqlKey.getTargetClass().isAnnotationPresent(FlysqlView.class)) {

				FlysqlView flysqlView = flysqlKey.getTargetClass().getAnnotation(FlysqlView.class);
				return " select "
						+ FlysqlConstants.SELECT_REPLACE_CONTENT
						+ " from ( "
						+ flysqlView.value()
						+ " ) flysql ";
			}

			throw new FlysqlException("view is not exist");
		} else {

			// normal select sql
			String tableName = FlysqlUtils.getTableName(flysqlKey.getTargetClass());
			return tableName == null
					? ""
					: "select " + FlysqlConstants.SELECT_REPLACE_CONTENT + " from " + tableName;
		}
	}

	/**
	 * init insert sql
	 *
	 * @param entity entity
	 * @return sqlString
	 * @since 0.0.1
	 */
	public String initSaveSql(A entity) {

		List<FieldBody> fieldBodies = ReflectUtils.queryFields(flysqlKey.getTargetClass());

		return "insert into "
				+ FlysqlUtils.getTableName(flysqlKey.getTargetClass())
				+ generateSqlCols(fieldBodies)
				+ " values "
				+ generateSqlValues(entity, fieldBodies);
	}

	/**
	 * init insert sql
	 *
	 * @param entity entity
	 * @return sqlString
	 * @since 0.0.1
	 */
	public String initBatchSaveSql(List<A> entity) {

		List<FieldBody> fieldBodies = ReflectUtils.queryFields(flysqlKey.getTargetClass());

		List<String> valueSqlList = new ArrayList<>();
		entity.forEach(e -> valueSqlList.add(generateSqlValues(e, fieldBodies)));

		return "insert into "
				+ FlysqlUtils.getTableName(flysqlKey.getTargetClass())
				+ generateSqlCols(fieldBodies)
				+ " values "
				+ Strings.join(valueSqlList, ',');
	}

	public String generateSqlCols(List<FieldBody> fieldBodies) {

		List<String> columnList = new ArrayList<>();
		for (FieldBody metaFieldBody : fieldBodies) {
			Field metaField = metaFieldBody.getField();
			columnList.add(columnsMap.get(metaField.getName()).getName());
		}
		return "(" + Strings.join(columnList, ',') + ")";
	}

	public String generateSqlValues(A entity, List<FieldBody> fieldBodies) {

		List<String> valueList = new ArrayList<>();

		for (FieldBody metaFieldBody : fieldBodies) {
			Object invoke;
			Field metaField = metaFieldBody.getField();
			if (metaField.isAnnotationPresent(CreatedBy.class)
					|| metaField.isAnnotationPresent(LastModifiedBy.class)) {
				invoke = getExecutorId();
			} else if (metaField.isAnnotationPresent(CreatedDate.class)
					|| metaField.isAnnotationPresent(LastModifiedDate.class)) {
				invoke = LocalDateTime.now();
			} else if (metaField.isAnnotationPresent(Version.class)) {
				invoke = 1;
			} else if (metaField.isAnnotationPresent(IsDelete.class)) {
				invoke = 0;
			} else {
				try {
					invoke = metaFieldBody.getReadMethod().invoke(entity);
				} catch (IllegalAccessException | InvocationTargetException e) {
					continue;
				}
			}

			if (metaField.isAnnotationPresent(RowId.class)) {
				if (invoke == null) {
					invoke = SnowflakeUtils.getNextUuid();
				}
			}

			if (invoke != null) {
				SqlValue sqlValue = SqlValueFactory.getSqlValue(flysqlKey.getDataBaseType());
				switch (metaField.getType().getName()) {
					case ReflectConstants.BOOLEAN:
					case ReflectConstants.BOOLEAN_LOWER:
						valueList.add(sqlValue.getBooleanValue(invoke.toString()));
						break;
					case ReflectConstants.DATE:
						valueList.add(sqlValue.getDateValue(invoke.toString()));
						break;
					case ReflectConstants.LOCAL_DATE:
						valueList.add(sqlValue.getLocalDateValue(invoke.toString()));
						break;
					case ReflectConstants.LOCAL_DATE_TIME:
						valueList.add(sqlValue.getLocalDateTimeValue(invoke.toString()));
						break;
					default:
						valueList.add(FlysqlExecute.addSingleQuote(invoke));
				}
			} else {
				valueList.add("NULL");
			}
		}
		return "( " + Strings.join(valueList, ',') + ")";
	}

	/**
	 * get executor id
	 *
	 * @return user id
	 * @since 0.0.1
	 */
	public String getExecutorId() {

		if (SecurityContextHolder.getContext() == null
				|| SecurityContextHolder.getContext().getAuthentication() == null) {
			return "anonymous";
		}

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal == null) {
			return "anonymous";
		}

		return String.valueOf(principal);
	}

	/**
	 * parse mongodb sql conditions
	 *
	 * @param sqlConditions sqlConditions
	 * @return sqlString
	 * @since 0.0.1
	 */
	public Criteria parseSqlConditions(List<SqlCondition> sqlConditions) {

		Criteria criteria = new Criteria();

		for (SqlCondition sqlConditionMeta : sqlConditions) {

			switch (sqlConditionMeta.getOperateType()) {
				case EQ:
					criteria.and(sqlConditionMeta.getColumnName()).is(sqlConditionMeta.getValue());
					break;
				case GT_EQ:
					criteria.and(sqlConditionMeta.getColumnName()).gte(sqlConditionMeta.getValue());
					break;
				case GT:
					criteria.and(sqlConditionMeta.getColumnName()).gt(sqlConditionMeta.getValue());
					break;
				case LT:
					criteria.and(sqlConditionMeta.getColumnName()).lt(sqlConditionMeta.getValue());
					break;
				case LT_EQ:
					criteria.and(sqlConditionMeta.getColumnName()).lte(sqlConditionMeta.getValue());
					break;
				case IN:
					criteria.and(sqlConditionMeta.getColumnName()).in(sqlConditionMeta.getValue());
					break;
				case NOT_IN:
					criteria.and(sqlConditionMeta.getColumnName()).ne(sqlConditionMeta.getValue());
					break;
				case OR:
					criteria.orOperator();
					break;
				case AND:
					criteria.andOperator();
					break;
			}
		}

		return criteria;
	}

	/**
	 * parse sql conditions
	 *
	 * @param sqlString sqlString
	 * @param sqlConditions sqlConditions
	 * @param sqlOrderByConditions sqlOrderByConditions
	 * @param executeType executeType
	 * @return sqlString
	 * @since 0.0.1
	 */
	public String parseSqlConditions(
			String sqlString,
			List<SqlCondition> sqlConditions,
			List<String> sqlOrderByConditions,
			String executeType) {

		StringBuilder sqlStringBuilder = new StringBuilder(sqlString);

		boolean selectFlag = true;
		boolean limitFlag = false;
		boolean whereFlag = false;
		Integer limitValue = null;

		SqlCondition sqlConditionTemp = null;

		for (SqlCondition sqlConditionMeta : sqlConditions) {

			switch (sqlConditionMeta.getOperateType()) {
				case SELECT:
					sqlStringBuilder =
							new StringBuilder(
									sqlStringBuilder
											.toString()
											.replace(
													FlysqlConstants.SELECT_REPLACE_CONTENT,
													String.valueOf(sqlConditionMeta.getValue())));
					selectFlag = false;
					break;
				case SET_VALUE:
					sqlStringBuilder =
							new StringBuilder(
									sqlStringBuilder
											.toString()
											.replace(
													sqlConditionMeta.getColumnName(),
													String.valueOf(sqlConditionMeta.getValue())));
					break;
				case OR:
				case AND:
				case AND_END:
				case AND_START:
					if (whereFlag) {
						sqlStringBuilder.append(sqlConditionMeta.getOperateType().getCode());
					} else {
						whereFlag = true;
						sqlStringBuilder.append(" where ( ");
					}
					break;
				case ORDER_BY:
					if (hasOperateType(sqlConditionTemp, SqlOperateType.ORDER_BY)) {
						sqlStringBuilder.append(",");
					} else {
						sqlStringBuilder.append(" order by ");
					}
					sqlStringBuilder
							.append(sqlConditionMeta.getColumnName())
							.append(" ")
							.append(sqlConditionMeta.getValue());
					break;
				case UPDATE:
					break;
				case SQL:
					if (executeType.equals("COUNT")) {
						sqlStringBuilder =
								new StringBuilder(
										" select count(1) from (" + sqlConditionMeta.getColumnName() + ") as alia ");
					} else {
						sqlStringBuilder =
								new StringBuilder(
										" select * from (" + sqlConditionMeta.getColumnName() + ") as alia ");
					}
					break;
				case LIMIT:
					limitFlag = true;
					limitValue = Integer.parseInt(String.valueOf(sqlConditionMeta.getValue()));
					break;
				default:
					if (hasOperateType(sqlConditionTemp, SQL)
							|| hasOperateType(sqlConditionTemp, UPDATE)
							|| hasOperateType(sqlConditionTemp, SqlOperateType.SELECT)
							|| hasOperateType(sqlConditionTemp, SqlOperateType.SET_VALUE)) {
						sqlStringBuilder.append(" where ");
						whereFlag = true;
					} else {
						if (!hasOperateType(sqlConditionTemp, AND_START)
								&& !hasOperateType(sqlConditionMeta, AND_START)
								&& !hasOperateType(sqlConditionMeta, AND_END)
								&& !hasOperateType(sqlConditionTemp, OR)
								&& !hasOperateType(sqlConditionTemp, AND)) {
							sqlStringBuilder.append(" and ");
						}
					}
					sqlStringBuilder
							.append(sqlConditionMeta.getColumnName())
							.append(sqlConditionMeta.getOperateType().getCode())
							.append(sqlConditionMeta.getValue());
					break;
			}
			sqlConditionTemp = sqlConditionMeta;
		}

		// last order by
		if (!sqlOrderByConditions.isEmpty()) {
			sqlStringBuilder.append(" order by ").append(Strings.join(sqlOrderByConditions, ','));
		}

		// last limit
		if (limitFlag) {
			sqlStringBuilder.append(" limit ").append(limitValue);
		}

		// 替换需要查询的字段
		if (selectFlag) {
			List<String> columnsList = new ArrayList<>();
			columnsMap.forEach((k, v) -> columnsList.add(v.getName() + " " + k));
			return sqlStringBuilder
					.toString()
					.replace(FlysqlConstants.SELECT_REPLACE_CONTENT, Strings.join(columnsList, ','));
		}

		return sqlStringBuilder.toString();
	}

	/**
	 * has use operate
	 *
	 * @param sqlCondition sqlCondition
	 * @param sqlOperateType sqlOperateType
	 * @return true has used
	 * @since 0.0.1
	 */
	public static Boolean hasOperateType(SqlCondition sqlCondition, SqlOperateType sqlOperateType) {

		if (sqlCondition == null) {
			return true;
		}
		return sqlCondition.getOperateType().equals(sqlOperateType);
	}

	/**
	 * print sql
	 *
	 * @param sql sql
	 */
	public void printSql(String sql) {
		if (flysqlKey.getFlysqlProperties().getShowLog()) {
			log.info("[oxygen-flysql-sql]:" + sql);
		} else {
			log.debug("[oxygen-flysql-sql]:" + sql);
		}
	}
}
