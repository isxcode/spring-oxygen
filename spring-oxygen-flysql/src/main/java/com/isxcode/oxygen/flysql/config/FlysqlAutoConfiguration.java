package com.isxcode.oxygen.flysql.config;

import com.isxcode.oxygen.flysql.constant.FlysqlConstants;
import com.isxcode.oxygen.flysql.core.Flysql;
import com.isxcode.oxygen.flysql.properties.FlysqlProperties;
import com.isxcode.oxygen.flysql.response.GlobalExceptionAdvice;
import com.isxcode.oxygen.flysql.response.SuccessResponseAdvice;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.Nullable;

/**
 * flysql auto configure
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
@EnableConfigurationProperties(FlysqlProperties.class)
public class FlysqlAutoConfiguration {

	/** init SuccessResponseAdvice */
	@Bean
	@ConditionalOnClass(FlysqlAutoConfiguration.class)
	private SuccessResponseAdvice initSuccessResponseAdvice(MessageSource messageSource) {

		return new SuccessResponseAdvice(messageSource);
	}

	/** init GlobalExceptionAdvice */
	@Bean
	@ConditionalOnClass(FlysqlAutoConfiguration.class)
	private GlobalExceptionAdvice initGlobalExceptionAdvice() {

		return new GlobalExceptionAdvice();
	}

	/**
	 * 初始化flysql factory
	 *
	 * @param flysqlProperties configs
	 * @param dataSourceProperties dataSourceProperties
	 * @param mongoTemplate mongoTemplate
	 * @since 0.0.1
	 */
	@Bean("flysql")
	@ConditionalOnClass(FlysqlAutoConfiguration.class)
	private Flysql flysql(
			FlysqlProperties flysqlProperties,
			@Nullable DataSourceProperties dataSourceProperties,
			@Nullable MongoTemplate mongoTemplate) {

		Map<String, JdbcTemplate> jdbcTemplateMap;
		Map<String, MongoTemplate> mongoTemplateMap;

		// 集成oracle/mysql/h2数据库
		Map<String, DataSourceProperties> dataSourcePropertiesMap = flysqlProperties.getDatasource();
		if (dataSourcePropertiesMap == null) {
			jdbcTemplateMap = new HashMap<>(1);
		} else {
			jdbcTemplateMap = new HashMap<>(dataSourcePropertiesMap.size() + 1);
			dataSourcePropertiesMap.forEach(
					(k, v) ->
							jdbcTemplateMap.put(k, new JdbcTemplate(v.initializeDataSourceBuilder().build())));
		}

		if (dataSourceProperties != null
				&& dataSourceProperties.initializeDataSourceBuilder() != null) {
			jdbcTemplateMap.put(
					FlysqlConstants.PRIMARY_DATASOURCE_NAME,
					new JdbcTemplate(dataSourceProperties.initializeDataSourceBuilder().build()));
		}

		// 集成mongodb数据库
		Map<String, MongoProperties> mongodbPropertiesMap = flysqlProperties.getMongodb();
		if (mongodbPropertiesMap == null) {
			mongoTemplateMap = new HashMap<>(1);
		} else {
			mongoTemplateMap = new HashMap<>(mongodbPropertiesMap.size() + 1);
			mongodbPropertiesMap.forEach(
					(k, v) -> {
						String connectSetting;
						if (v.getUri() == null) {
							connectSetting =
									"mongo://"
											+ v.getUsername()
											+ ":"
											+ String.valueOf(v.getPassword())
											+ "@"
											+ v.getHost()
											+ ":"
											+ v.getPort()
											+ "/"
											+ v.getDatabase();
						} else {
							connectSetting = v.getUri();
						}
						mongoTemplateMap.put(
								k, new MongoTemplate(new SimpleMongoClientDatabaseFactory(connectSetting)));
					});
		}
		if (mongoTemplate != null) {
			mongoTemplateMap.put(FlysqlConstants.PRIMARY_DATASOURCE_NAME, mongoTemplate);
		}

		// 储存关系型数据库和非关系型数据库
		return new Flysql(jdbcTemplateMap, mongoTemplateMap, flysqlProperties);
	}
}
