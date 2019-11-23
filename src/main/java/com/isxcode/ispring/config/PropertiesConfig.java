package com.isxcode.ispring.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 项目的属性配置中心 并不能把所有的属性发到一个下面  必须去实现一个系统就像Exception一样 写一个异常系统
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019-11-14
 */
@Data
@Component
@ConfigurationProperties(prefix = "isxcode")
public class PropertiesConfig {

    /**
     * 文件下载路径
     */
    private String location;

    private String jwtSecret;

}
