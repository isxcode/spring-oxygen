package com.isxcode.ispring.wechatgo.model;

import lombok.Data;

import java.util.List;

@Data
public class WeChatUnionId {

    private Integer subscribe;

    private String openid;

    private String nickname;

    private Integer sex;

    private String language;

    private String city;

    private String province;

    private String country;

    private String headimgurl;

    private Integer subscribe_time;

    private String unionid;

    private String remark;

    private String groupid;

    private List<String> tagid_list;

    private String subscribe_scene;

    private Integer qr_scene;

    private String qr_scene_str;
}
