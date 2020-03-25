package com.ispong.oxygen.module.file;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 文件配置中心
 *
 * @author ispong
 * @since 0.0.1
 */
@Data
@Component
@ConfigurationProperties("oxygen.system.file")
public class FileProperties {

    private String location;
}
