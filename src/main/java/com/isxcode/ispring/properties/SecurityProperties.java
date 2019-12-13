package com.isxcode.ispring.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 加密配置
 * 
 * @author ispong
 * @version v0.1.0
 * @date
 */
@Data
@Component
@ConfigurationProperties(prefix = "isxcode.security")
public class SecurityProperties {


    /**
     * jwt AES 加密钥匙
     */
    private String jwtSecurityKey;

}
