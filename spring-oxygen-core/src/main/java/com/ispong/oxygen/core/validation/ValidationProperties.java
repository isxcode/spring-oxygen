package com.ispong.oxygen.core.validation;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 发送手机验证码配置中心
 *
 * @author ispong
 * @since 0.0.1
 */
@Data
@Component
@ConfigurationProperties("oxygen.system.validation")
public class ValidationProperties {

    /**
     * 短信服务器地址
     */
    private String url;

    /**
     * 短信账号
     */
    private String account;

    /**
     * 账号密码
     */
    private String password;

    /**
     * 是否记录报告
     */
    private Boolean report;
}
