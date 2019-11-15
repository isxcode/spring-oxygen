package com.isxcode.ispring.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 项目的属性配置中心
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019-11-14
 */
@Data
@ConfigurationProperties(prefix = "isxcode")
public class PropertiesConfig {

    /**
     * 文件下载路径
     */
    private String location;

}
