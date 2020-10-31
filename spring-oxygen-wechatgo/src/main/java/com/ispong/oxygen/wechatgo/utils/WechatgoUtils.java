package com.ispong.oxygen.wechatgo.utils;

import com.ispong.oxygen.wechatgo.pojo.entity.WeChatEventBody;

public class WechatgoUtils {

    public static String getTextMessage(WeChatEventBody weChatEventBody, String text) {

        return "<xml>\n" +
            "  <ToUserName><![CDATA[" + weChatEventBody.getFromUserName() + "]]></ToUserName>\n" +
            "  <FromUserName><![CDATA[" + weChatEventBody.getToUserName() + "]]></FromUserName>\n" +
            "  <CreateTime>" + System.currentTimeMillis() + "</CreateTime>\n" +
            "  <MsgType><![CDATA[text]]></MsgType>\n" +
            "  <Content><![CDATA[" + text + "]]></Content>\n" +
            "</xml>";
    }
}
