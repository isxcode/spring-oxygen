package com.ispong.oxygen.common.validation;

import lombok.Data;

/**
 * 手机发送接口请求对象
 *
 * @author ispong
 * @since 0.0.1
 */
@Data
public class PhoneCodeReq {

    /**
     * 手机号
     */
    private String phone;

    /**
     * 消息内容
     */
    private String msg;

    /**
     * 服务器url
     */
    private String url;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 是否使用报告
     */
    private Boolean report;

}
