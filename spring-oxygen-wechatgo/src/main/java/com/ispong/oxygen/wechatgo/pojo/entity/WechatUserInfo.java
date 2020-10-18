package com.ispong.oxygen.wechatgo.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WechatUserInfo {

    @JsonSetter("subscribe")
    private String subscribe;

    @JsonSetter("nickname")
    private String nickName;

    private String province;

    private String city;

    @JsonSetter("openid")
    private String openId;

    private String language;

    private Integer sex;

    @JsonSetter("headimgurl")
    private String headImgUrl;

    private String country;

}