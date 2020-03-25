package com.ispong.oxygen.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 系统配置文件
 *
 * @author ispong
 * @since 0.0.1
 */
@Data
@ConfigurationProperties("oxygen.file")
public class OxygenProperties {

    /**
     * 文件下载路径
     */
    private String location = "D:\\file-data";
}
