package com.ispong.oxygen.scheduler;

import lombok.Data;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 定时器配置
 *
 * @author ispong
 * @since 0.0.1
 */
@Data
@Component
@ConfigurationProperties("oxygen.system.quartz")
public class QuartzProperties {

    private DataSourceProperties datasource;
}
