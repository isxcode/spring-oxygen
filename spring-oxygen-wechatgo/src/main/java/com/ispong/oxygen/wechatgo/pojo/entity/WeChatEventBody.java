/*
 * Copyright [2020] [ispong]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ispong.oxygen.wechatgo.pojo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import static com.ispong.oxygen.wechatgo.pojo.constant.WechatgoConstants.SLOPE_N;

/**
 * 微信事件体
 *
 * @author ispong
 * @since  0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WeChatEventBody extends DefaultHandler {

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

    /**
     * 消息模板消息
     */
    private String msgId;

    /**
     * 消息模板状态
     */
    private String status;

    private String localName;

    private String ticket;

    /**
     * xml解析工具
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        this.localName = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) {

        String date = new String(ch, start, length);
        if (!SLOPE_N.equals(date)) {
            switch (this.localName) {
                case "ToUserName":
                    this.toUserName = date;
                    break;
                case "FromUserName":
                    this.fromUserName = date;
                    break;
                case "CreateTime":
                    this.createTime = Integer.parseInt(date);
                    break;
                case "MsgType":
                    this.msgType = date;
                    break;
                case "Event":
                    this.event = date;
                    break;
                case "MsgId":
                    this.msgId = date;
                    break;
                case "Status":
                    this.status = date;
                    break;
                case "Ticket":
                    this.ticket = date;
                    break;
                default:
            }
        }

    }
}

