package com.isxcode.isxcodespring.moudle.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * IsxcodeProperties
 *
 * @author ispong
 * @date 2019/10/16
 * @version v0.1.0
 */
@Data
@ConfigurationProperties("file")
public class IsxcodeProperties {

    /**
     * 文件下载路径
     */
    private String location = "/file-data/";

//    private String location = "D://";
}
