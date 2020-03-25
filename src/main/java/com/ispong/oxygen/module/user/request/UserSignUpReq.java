package com.ispong.oxygen.module.user.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户注册请求对象
 *
 * @author ispong
 * @since 0.0.1
 */
@Data
public class UserSignUpReq {

    @ApiModelProperty("账号")
    private String account;

    private String userName;

    private String password;

    private String phone;
}
