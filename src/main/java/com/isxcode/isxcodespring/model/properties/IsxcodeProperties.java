package com.isxcode.isxcodespring.model.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * IsxcodeProperties
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019/10/16
 */
@Data
@ConfigurationProperties("file")
public class IsxcodeProperties {

    /**
     * 文件下载路径
     */
    private String location = "D:\\file-data";

    /**
     * rabbitMq queue name
     */
    private String queueName = "isxcode-queue";

    /**
     * rabbitMq exchange name
     */
    private String exchangeName = "isxcode-exchange";

    /**
     * rabbitMq exchange name
     */
    private String routingKey = "isxcode-key";
}
