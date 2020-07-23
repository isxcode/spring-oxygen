package com.ispong.oxygen.core.validation;

import com.ispong.oxygen.core.exception.OxygenException;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 手机验证码工具类
 *
 * @author ispong
 * @since 0.0.1
 */
@Component
public class ValidationUtils {

    private static ValidationProperties validationProperties;

    private static RestTemplate restTemplate;

    public ValidationUtils(RestTemplateBuilder restTemplateBuilder, ValidationProperties validationProperties) {

        ValidationUtils.validationProperties = validationProperties;
        ValidationUtils.restTemplate = restTemplateBuilder.build();
    }

    /**
     * 发送手机验证码
     *
     * @param phoneCodeReq 手机号请求对象
     * @return PhoneCodeRes
     * @throws OxygenException 手机发送异常
     * @since 0.0.1
     */
    public static PhoneCodeRes sendPhoneCode(PhoneCodeReq phoneCodeReq) throws OxygenException {

        try {
            BeanUtils.copyProperties(validationProperties, phoneCodeReq);
            return restTemplate.postForObject(phoneCodeReq.getUrl(), phoneCodeReq, PhoneCodeRes.class);
        } catch (Exception e) {
            throw new OxygenException("发送手机号异常");
        }

    }
}
