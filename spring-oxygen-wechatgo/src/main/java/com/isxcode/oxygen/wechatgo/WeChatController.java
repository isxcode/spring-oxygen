package com.isxcode.oxygen.wechatgo;

import com.isxcode.oxygen.wechatgo.model.WeChatEventBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * wechat server config
 *
 * @author ispong
 * @version v0.1.0
 * @date 2020-01-14
 */
@Slf4j
@RestController
@RequestMapping("weChatServer")
public class WeChatController {

    private final WeChatService weChatService;

    private final WeChatProperties weChatProperties;

    public WeChatController(WeChatProperties weChatProperties, WeChatServiceImpl weChatService) {

        this.weChatProperties = weChatProperties;
        this.weChatService = weChatService;
    }

    /**
     * get接口-微信服务器认证
     *
     * @param echostr 微信的认证值
     * @return 返回微信的认证值
     * @since 2020-01-14
     */
    @GetMapping("/weChatListen")
    public String weChatAuthentication(
            @RequestParam("echostr") String echostr,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("nonce") String nonce,
            @RequestParam("signature") String signature) {

        log.info("进入get weChatListen");

        if (weChatService.checkWeChat(nonce, timestamp, signature, weChatProperties.getEnv())) {

            return echostr;
        }

        throw new WeChatException("不是微信发送的请求");
    }

    /**
     * post接口-接受微信事件(和get接口地址保持一直)
     *
     * @param httpServletRequest servlet请求体
     * @since 2020-01-14
     */
    @PostMapping("/weChatListen")
    public void weChatListen(HttpServletRequest httpServletRequest) {

        // 获取事件信息
        WeChatEventBody weChatEventBody = WeChatUtils.parseWeChatXml(httpServletRequest, WeChatEventBody.class);

        // 判断事件
        switch (weChatEventBody.getEvent()) {
            case "subscribe":
                // 发送模板

                break;
            case "unsubscribe":
                // 发送模板

                break;
            default:
        }

    }
}
