package com.isxcode.ispring.wechatgo.model;

import lombok.Data;

/**
 * 微信的配置
 *
 * @author ispong
 * @version v0.1.0
 * @date 2020-01-14
 */
@Data
public class WeChatAppInfo {

    /**
     * appId
     */
    private String appId = "";

    /**
     * appSecret
     */
    private String appSecret = "";

    /**
     * 服务器配置token
     */
    private String token = "";

    /**
     * 微信服务器地址
     */
    private String url = "https://api.weixin.qq.com";

}
