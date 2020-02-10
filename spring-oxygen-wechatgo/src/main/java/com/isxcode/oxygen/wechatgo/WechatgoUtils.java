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
package com.isxcode.oxygen.wechatgo;

import com.isxcode.oxygen.core.httpclient.HttpClientUtils;

import static com.isxcode.oxygen.wechatgo.WechatgoProperties.WE_CHAT_ACCESS_TOKEN;

/**
 * wechat utils
 *
 * @author ispong
 * @version v0.1.0
 */
public class WechatgoUtils {

    private static WechatgoProperties wechatgoProperties;

    public WechatgoUtils(WechatgoProperties wechatgoProperties) {

        WechatgoUtils.wechatgoProperties = wechatgoProperties;
    }

    /**
     * 发送微信模板(正常发送模板)
     *
     * @param openId     接收者openid
     * @param templateId 模板ID
     * @param data       模板数据
     * @since 2020-02-04
     */
    public static void sendMsgTemplate(String openId, String templateId, String data) {

        String requestParam = " {\n" +
                "           \"touser\":\"" + openId + "\",\n" +
                "           \"template_id\":\"" + templateId + "\",\n" +
                "           \"data\":\n" + data.replace("\n", "\\n") +
                "       }";

        try {
            HttpClientUtils.doPost(wechatgoProperties.getUrl() + "/cgi-bin/message/template/send" + "?access_token=" + WE_CHAT_ACCESS_TOKEN, requestParam);
        } catch (Exception e) {
            throw new WechatgoException("send template fail");
        }

    }

    /**
     * 发送微信模板(带url发送模板)
     *
     * @param openId     接收者openid
     * @param templateId 模板ID
     * @param data       模板数据
     * @param url        模板跳转链接（海外帐号没有跳转能力）
     * @since 2020-02-04
     */
    public static void sendMsgTemplate(String openId, String templateId, String url, String data) {

        String requestParam = " {\n" +
                "           \"touser\":\"" + openId + "\",\n" +
                "           \"url\":\"" + url + "\",  \n" +
                "           \"template_id\":\"" + templateId + "\",\n" +
                "           \"data\":\n" + data.replace("\n", "\\n") +
                "       }";

        try {
            HttpClientUtils.doPost(wechatgoProperties.getUrl() + "/cgi-bin/message/template/send" + "?access_token=" + WE_CHAT_ACCESS_TOKEN, requestParam);
        } catch (Exception e) {
            throw new WechatgoException("send template fail");
        }
    }

    /**
     * 发送微信模板(正常发送模板)
     *
     * @param openId     接收者openid
     * @param templateId 模板ID
     * @param data       模板数据
     * @param appId      所需跳转到的小程序appid（该小程序appid必须与发模板消息的公众号是绑定关联关系，暂不支持小游戏）
     * @param pagePath   所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar），要求该小程序已发布，暂不支持小游戏
     * @param url        模板跳转链接（海外帐号没有跳转能力）
     * @since 2020-02-04
     */
    public static void sendMsgTemplate(String openId, String templateId, String url, String appId, String pagePath, String data) {

        String requestParam = " {\n" +
                "           \"touser\":\"" + openId + "\",\n" +
                "           \"template_id\":\"" + templateId + "\",\n" +
                "           \"url\":\"" + url + "\",  \n" +
                "           \"miniprogram\":{\n" +
                "             \"appid\":\"" + appId + "\",\n" +
                "             \"pagepath\":\"" + pagePath + "\"\n" +
                "           },          \n" +
                "           \"data\":\n" + data.replace("\n", "\\n") +
                "       }";

        try {
            HttpClientUtils.doPost(wechatgoProperties.getUrl() + "/cgi-bin/message/template/send" + "?access_token=" + WE_CHAT_ACCESS_TOKEN, requestParam);
        } catch (Exception e) {
            throw new WechatgoException("send template fail");
        }

    }

    /**
     * 发送微信模板(正常发送模板)
     *
     * @param openId     接收者openid
     * @param templateId 模板ID
     * @param data       模板数据
     * @param appId      所需跳转到的小程序appid（该小程序appid必须与发模板消息的公众号是绑定关联关系，暂不支持小游戏）
     * @param pagePath   所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar），要求该小程序已发布，暂不支持小游戏
     * @since 2020-02-04
     */
    public static void sendMsgTemplate(String openId, String templateId, String appId, String pagePath, String data) {

        String requestParam = " {\n" +
                "           \"touser\":\"" + openId + "\",\n" +
                "           \"template_id\":\"" + templateId + "\",\n" +
                "           \"miniprogram\":{\n" +
                "             \"appid\":\"" + appId + "\",\n" +
                "             \"pagepath\":\"" + pagePath + "\"\n" +
                "           },          \n" +
                "           \"data\":\n" + data.replace("\n", "\\n") +
                "       }";

        try {
            HttpClientUtils.doPost(wechatgoProperties.getUrl() + "/cgi-bin/message/template/send" + "?access_token=" + WE_CHAT_ACCESS_TOKEN, requestParam);
        } catch (Exception e) {
            throw new WechatgoException("send template fail");
        }

    }
}
