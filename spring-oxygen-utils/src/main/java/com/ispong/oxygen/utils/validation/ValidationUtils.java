package com.ispong.oxygen.utils.validation;

import com.ispong.oxygen.utils.exception.CoreException;
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
     * @throws CoreException 手机发送异常
     * @since 0.0.1
     */
    public static PhoneCodeRes sendPhoneCode(PhoneCodeReq phoneCodeReq) throws CoreException {

        try {
            BeanUtils.copyProperties(validationProperties, phoneCodeReq);
            return restTemplate.postForObject(phoneCodeReq.getUrl(), phoneCodeReq, PhoneCodeRes.class);
        } catch (Exception e) {
            throw new CoreException("发送手机号异常");
        }

    }
}
