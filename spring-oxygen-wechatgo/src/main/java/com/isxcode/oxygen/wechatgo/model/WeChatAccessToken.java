package com.isxcode.oxygen.wechatgo.model;

import lombok.Data;

/**
 * wechat token model
 *
 * @author ispong
 * @version v0.1.0
 * @date 2020-01-16
 */
@Data
public class WeChatAccessToken {

    /**
     * 获取到的凭证
     */
    private String access_token;

    /**
     * 凭证有效时间，单位：秒
     */
    private Integer expires_in;

    /**
     * 返回错误码
     */
    private Integer errcode = 0;

    /**
     * 错误信息
     */
    private String errmsg;
}
