package com.isxcode.oxygen.flysql.config;

import com.isxcode.oxygen.flysql.core.Flysql;
import com.isxcode.oxygen.flysql.constant.FlysqlConstants;
import com.isxcode.oxygen.flysql.properties.FlysqlDataSourceProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * flysql auto configure
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
@EnableConfigurationProperties(FlysqlDataSourceProperties.class)
public class FlysqlAutoConfiguration {

    /**
     * init datasource
     *
     * @param flysqlDataSourceProperties datasource configs
     * @param jdbcTemplate               jdbcTemplate
     * @since 0.0.1
     */
    @Bean
    @ConditionalOnClass(FlysqlAutoConfiguration.class)
    private Flysql initFlySqlFactory(FlysqlDataSourceProperties flysqlDataSourceProperties, JdbcTemplate jdbcTemplate) {

        Map<String, JdbcTemplate> jdbcTemplateMap;

        Map<String, DataSourceProperties> dataSourcePropertiesMap = flysqlDataSourceProperties.getDatasource();
        if (dataSourcePropertiesMap == null) {
            jdbcTemplateMap = new HashMap<>(1);
        } else {
            jdbcTemplateMap = new HashMap<>(dataSourcePropertiesMap.size() + 1);
            dataSourcePropertiesMap.forEach((k, v) -> jdbcTemplateMap.put(k, new JdbcTemplate(v.initializeDataSourceBuilder().build())));
        }

        jdbcTemplateMap.put(FlysqlConstants.PRIMARY_DATASOURCE_NAME, jdbcTemplate);
        return new Flysql(jdbcTemplateMap);
    }

}
