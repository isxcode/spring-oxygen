package com.isxcode.oxygen.flysql.core;

import com.isxcode.oxygen.flysql.constant.FlysqlConstants;
import com.isxcode.oxygen.flysql.properties.FlysqlProperties;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * init Flysql
 *
 * @author ispong
 * @version 0.0.2
 */
public class Flysql {

	/** storage jdbc template */
	private final Map<String, JdbcTemplate> jdbcTemplateMap;

	/** storage mongo template */
	private final Map<String, MongoTemplate> mongdTemplateMap;

	/** flysql config */
	public final FlysqlProperties flysqlProperties;

	public Flysql(
			Map<String, JdbcTemplate> jdbcTemplateMap,
			Map<String, MongoTemplate> mongdTemplateMap,
			FlysqlProperties flysqlProperties) {

		this.flysqlProperties = flysqlProperties;
		this.mongdTemplateMap = mongdTemplateMap;
		this.jdbcTemplateMap = jdbcTemplateMap;
	}

	/**
	 * build jdbc Flysql
	 *
	 * @return FlysqlBuilder
	 */
	public FlysqlBuilder build() {

		return build(FlysqlConstants.PRIMARY_DATASOURCE_NAME);
	}

	/**
	 * build jdbc Flysql
	 *
	 * @param dataBaseName dataBaseName
	 * @return FlysqlBuilder
	 */
	public FlysqlBuilder build(String dataBaseName) {

		return new FlysqlBuilder(jdbcTemplateMap.get(dataBaseName), flysqlProperties);
	}

	/**
	 * build mongo Flysql
	 *
	 * @return FlysqlBuilder
	 */
	public FlysqlBuilder buildMongo() {

		return buildMongo(FlysqlConstants.PRIMARY_DATASOURCE_NAME);
	}

	/**
	 * build mongo Flysql
	 *
	 * @param dataBaseName dataBaseName
	 * @return FlysqlBuilder
	 */
	public FlysqlBuilder buildMongo(String dataBaseName) {

		return new FlysqlBuilder(mongdTemplateMap.get(dataBaseName), flysqlProperties);
	}

	/**
	 * get default datasource
	 *
	 * @return DataSource
	 */
	public DataSource getDefaultDataSource() {

		return jdbcTemplateMap.get(FlysqlConstants.PRIMARY_DATASOURCE_NAME).getDataSource();
	}
}
