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
import com.isxcode.oxygen.wechatgo.model.WeChatAccessToken;
import com.isxcode.oxygen.wechatgo.model.WeChatEventBody;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * wechatgo WechatgoServiceImpl
 *
 * @author ispong
 * @version v0.1.0
 */
@Slf4j
public class WechatgoServiceImpl implements WechatgoService {

    /**
     * temp token
     */
    public static String WE_CHAT_ACCESS_TOKEN = "";

    private WechatgoEventHandler wechatgoEventHandler;

    public void setWechatgoEventHandler(WechatgoEventHandler wechatgoEventHandler) {
        this.wechatgoEventHandler = wechatgoEventHandler;
    }

    private final WechatgoProperties wechatgoProperties;

    public WechatgoServiceImpl(WechatgoProperties wechatgoProperties) {

        this.wechatgoProperties = wechatgoProperties;
    }

    @Override
    public Boolean checkWeChat(String nonce, String timestamp, String signature) {

        // 获取token
        String token = wechatgoProperties.getToken();

        // token,timestamp,nonce字典序排序得字符串list
        String[] strings = {token, timestamp, nonce};
        Arrays.sort(strings);

        // 哈希算法加密(SHA1)list得到hashcode
        String hashcode = DigestUtils.sha1Hex((strings[0] + strings[1] + strings[2]));

        // hashcode == signature ?
        return signature.equals(hashcode);
    }

    @Override
    public WeChatAccessToken getAccessToken() {

        // 配置请求参数
        Map<String, String> requestMap = new HashMap<>(3);
        requestMap.put("grant_type", "client_credential");
        requestMap.put("appid", wechatgoProperties.getAppId());
        requestMap.put("secret", wechatgoProperties.getAppSecret());

        // 获取返回体
        WeChatAccessToken weChatAccessToken = HttpClientUtils.doGet(wechatgoProperties.getUrl() + "/cgi-bin/token", requestMap, WeChatAccessToken.class);

        // 返回体处理
        switch (weChatAccessToken.getErrcode()) {
            case -1:
                // 系统繁忙，此时请开发者稍候再试(5秒)
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {

                }
                return getAccessToken();
            case 0:
                // 请求成功
                return weChatAccessToken;
            default:
                throw new WechatgoException(weChatAccessToken.getErrmsg());
        }
    }

    @Override
    public void handlerWechatEvent(WeChatEventBody weChatEventBody) {

        switch (weChatEventBody.getEvent()) {
            case "subscribe":
                log.debug("event subscribe");
                wechatgoEventHandler.subscribeEvent(weChatEventBody);
                break;
            case "unsubscribe":
                log.debug("event unsubscribe");
                wechatgoEventHandler.unsubscribeEvent(weChatEventBody);
                break;
            case "TEMPLATESENDJOBFINISH":
                log.debug("event send template success");
                wechatgoEventHandler.sendMsgTemplateResponse(weChatEventBody);
                break;
                default:
                log.debug("event nothing");
        }
    }

}
