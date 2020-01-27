package com.isxcode.oxygen.wechatgo.model;

import lombok.Data;

/**
 * 微信推送返回体
 * 
 * @author ispong
 * @version v0.1.0
 * @date 2020-01-14
 */
@Data
public class WeChatEventBody {

    /**
     * 开发者微信号
     */
    private String toUserName;

    /**
     * 发送方帐号（一个OpenID）
     */
    private String fromUserName;

    /**
     * 消息创建时间 （整型）
     */
    private Integer createTime;

    /**
     * 消息类型，event
     */
    private String msgType;

    /**
     * 事件类型，subscribe(订阅)、unsubscribe(取消订阅)
     */
    private String event;

    /**
     * 事件KEY值，qrscene_为前缀，后面为二维码的参数值
     */
    private String eventKey;
}
