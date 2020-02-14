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
package com.ispong.oxygen.wechatgo;

import com.ispong.oxygen.wechatgo.model.WeChatAccessToken;
import com.ispong.oxygen.wechatgo.model.WeChatEventBody;
import com.ispong.oxygen.wechatgo.utils.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    private final WechatgoEventHandler wechatgoEventHandler;

    private final WechatgoProperties wechatgoProperties;

    public WechatgoServiceImpl(WechatgoProperties wechatgoProperties, WechatgoEventHandler wechatgoEventHandler) {

        this.wechatgoEventHandler = wechatgoEventHandler;
        this.wechatgoProperties = wechatgoProperties;
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
        switch (weChatAccessToken.getErrCode()) {
            case -1:
                // 系统繁忙，此时请开发者稍候再试(5秒)
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {

                }
                return getAccessToken();
            case 0:
                // 请求成功
                log.debug("wechat token" + weChatAccessToken);
                return weChatAccessToken;
            default:
                throw new WechatgoException(weChatAccessToken.getErrMsg());
        }
    }

    @Override
    public Boolean checkWeChat(String nonce, String timestamp, String signature) {

        // 获取token
        String token = wechatgoProperties.getToken();
        if (token == null) {
            throw new WechatgoException("please config wechatgo properties : 'token'");
        }

        // token,timestamp,nonce字典序排序得字符串list
        String[] strings = {token, timestamp, nonce};
        Arrays.sort(strings);

        try {
            // 哈希算法加密(SHA1)list得到hashcode
            MessageDigest sha1 = MessageDigest.getInstance("SHA1");
            sha1.update((strings[0] + strings[1] + strings[2]).getBytes());
            byte[] digest = sha1.digest();
            char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            char[] hashcode = new char[digest.length * 2];
            int index = 0;
            for (byte b : digest) {
                hashcode[index++] = hexDigits[(b >>> 4) & 0xf];
                hashcode[index++] = hexDigits[b & 0xf];
            }
            log.debug("hashcode" + new String(hashcode) + "--signature" + signature);

            // hashcode == signature ?
            return signature.equals(new String(hashcode));
        } catch (NoSuchAlgorithmException e) {
            throw new WechatgoException("sha1 parse fail");
        }

    }

    @Override
    public void handlerWechatEvent(WeChatEventBody weChatEventBody) {

        switch (String.valueOf(weChatEventBody.getEvent())) {
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
