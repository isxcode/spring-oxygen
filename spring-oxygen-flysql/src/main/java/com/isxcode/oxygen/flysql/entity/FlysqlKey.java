package com.isxcode.oxygen.flysql.entity;

import com.isxcode.oxygen.flysql.enums.DataBaseType;
import com.isxcode.oxygen.flysql.enums.SqlType;
import com.isxcode.oxygen.flysql.properties.FlysqlProperties;
import lombok.Data;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * flysql的构造器的核心要素
 *
 * @author ispong
 * @since 0.0.1
 */
@Data
public class FlysqlKey<A> {

	/** 临时返回对象 */
	private Class<A> targetClass;

	/** 数据源对应的jdbcTemplate */
	private JdbcTemplate jdbcTemplate;

	/** 视图名称 */
	private String viewSqlName;

	/** 执行器类型 */
	private SqlType sqlType;

	/** 数据源对应的mongoTemplate */
	private MongoTemplate mongoTemplate;

	/** 数据源类型 */
	private DataBaseType dataBaseType;

	/** 暂时日志 */
	private FlysqlProperties flysqlProperties;

	public FlysqlKey(
			DataBaseType dataBaseType,
			SqlType sqlType,
			JdbcTemplate jdbcTemplate,
			Class<A> targetClass,
			String viewSqlName,
			FlysqlProperties flysqlProperties) {

		this.flysqlProperties = flysqlProperties;
		this.targetClass = targetClass;
		this.jdbcTemplate = jdbcTemplate;
		this.viewSqlName = viewSqlName;
		this.sqlType = sqlType;
		this.dataBaseType = dataBaseType;
	}

	public FlysqlKey(
			DataBaseType dataBaseType,
			SqlType sqlType,
			JdbcTemplate jdbcTemplate,
			Class<A> targetClass,
			FlysqlProperties flysqlProperties) {

		this.flysqlProperties = flysqlProperties;
		this.targetClass = targetClass;
		this.jdbcTemplate = jdbcTemplate;
		this.sqlType = sqlType;
		this.dataBaseType = dataBaseType;
	}

	public FlysqlKey(
			DataBaseType dataBaseType,
			SqlType sqlType,
			MongoTemplate mongoTemplate,
			Class<A> targetClass,
			FlysqlProperties flysqlProperties) {

		this.flysqlProperties = flysqlProperties;
		this.targetClass = targetClass;
		this.mongoTemplate = mongoTemplate;
		this.sqlType = sqlType;
		this.dataBaseType = dataBaseType;
	}
}
