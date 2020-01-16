package com.isxcode.ispring.wechatgo;

import com.isxcode.ispring.exception.IsxcodeException;
import com.isxcode.ispring.utils.HttpClientUtils;
import com.isxcode.ispring.wechatgo.model.WeChatAccessToken;
import com.isxcode.ispring.wechatgo.model.WeChatAppInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信实现类
 *
 * @author ispong
 * @version v0.1.0
 * @date 2020-01-14
 */
@Slf4j
@Component
public class WeChatServiceImpl implements WeChatService {

    private WeChatProperties weChatProperties;

    @Autowired
    public void setWeChatProperties(WeChatProperties weChatProperties) {

        this.weChatProperties = weChatProperties;
    }

    @Override
    public Boolean checkWeChat(String nonce, String timestamp, String signature, String env) {

        // 获取token
        String token = weChatProperties.getApps().get(env).getToken();

        // token,timestamp,nonce字典序排序得字符串list
        String[] strings = {token, timestamp, nonce};
        Arrays.sort(strings);

        // 哈希算法加密(SHA1)list得到hashcode
        String hashcode = DigestUtils.sha1Hex((strings[0] + strings[1] + strings[2]));

        // hashcode == signature ?
        return signature.equals(hashcode);
    }

    @Override
    public void sendTemplate(String openId, String templateId, String env) {

        WeChatAccessToken accessToken = getAccessToken(env);
    }

    @Override
    public WeChatAccessToken getAccessToken(String env) {

        // 获取配置信息
        WeChatAppInfo weChatAppConfig = weChatProperties.getApps().get(env);

        // 配置请求参数
        Map<String, String> requestMap = new HashMap<>(3);
        requestMap.put("grant_type", "client_credential");
        requestMap.put("appid", weChatAppConfig.getAppId());
        requestMap.put("secret", weChatAppConfig.getAppSecret());

        // 获取返回体
        WeChatAccessToken weChatAccessToken = HttpClientUtils.doGet(weChatAppConfig.getUrl() + "/cgi-bin/token", requestMap, WeChatAccessToken.class);

        // 返回体处理
        switch (weChatAccessToken.getErrcode()) {
            case -1:
                // 系统繁忙，此时请开发者稍候再试(5秒)
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {

                }
                return getAccessToken(env);
            case 0:
                // 请求成功
                return weChatAccessToken;
            default:
                throw new IsxcodeException(weChatAccessToken.getErrmsg());
        }

    }

//    @Scheduled(cron = "0 */2 * * * ?")
//    public void test() {
//
//        System.out.println("print");
//    }

}
