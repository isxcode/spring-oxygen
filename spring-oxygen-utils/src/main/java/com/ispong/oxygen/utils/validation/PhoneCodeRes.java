package com.ispong.oxygen.utils.validation;

import lombok.Data;

/**
 * 手机验证码返回对象
 *
 * @author ispong
 * @since 0.0.1
 */
@Data
public class PhoneCodeRes {

    private String phone;

    private String codeType;

    private String code;

    private String msgId;
}
