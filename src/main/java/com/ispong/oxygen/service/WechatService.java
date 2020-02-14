package com.ispong.oxygen.service;

import com.ispong.oxygen.wechatgo.WechatgoEventHandler;
import com.ispong.oxygen.wechatgo.WechatgoTemplate;
import com.ispong.oxygen.wechatgo.model.WeChatEventBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 实现接口
 *
 * @author ispong
 * @version v0.1.0
 * @date 2020-02-04
 */
@Slf4j
@Service
public class WechatService implements WechatgoEventHandler {

    @Autowired
    private WechatgoTemplate wechatgoTemplate;

    @Override
    public void subscribeEvent(WeChatEventBody weChatEventBody) {

        String data = "{\n" +
                "           \"touser\":\"oLRflwyj79FUS7O7zARJsLq16k5k\",\n" +
                "           \"template_id\":\"KQwJbCIjdJ9wvktUBVxx2fVm44QdSsqNNyqU7kqnsHg\",\n" +
                "           \"data\":{\n" +
                "                   \"first\": {\n" +
                "                       \"value\":\"恭喜你购买成功！\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword1\":{\n" +
                "                       \"value\":\"巧克力\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword2\": {\n" +
                "                       \"value\":\"39.8元\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"remark\":{\n" +
                "                       \"value\":\"欢迎再次购买！\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   }\n" +
                "           }\n" +
                "       }";
        wechatgoTemplate.sendMsgTemplate(data);
        System.out.println("用户订阅" + weChatEventBody);
    }

    @Override
    public void unsubscribeEvent(WeChatEventBody weChatEventBody) {
        System.out.println("用户取消订阅" + weChatEventBody);
    }
}
