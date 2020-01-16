package com.isxcode.ispring.wechatgo;

import com.isxcode.ispring.exception.IsxcodeException;
import com.isxcode.ispring.utils.FormatUtils;
import com.isxcode.ispring.wechatgo.model.WeChatEventBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 微信-服务器配置
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

    public WeChatController(WeChatServiceImpl weChatService) {

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

        if (weChatService.checkWeChat(nonce, timestamp, signature, "test")) {

            return echostr;
        }

        throw new IsxcodeException("不是微信发送的请求");
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
        WeChatEventBody weChatEventBody = FormatUtils.parseWeChatXml(httpServletRequest, WeChatEventBody.class);

        // 判断事件
        switch (weChatEventBody.getEvent()) {
            case "subscribe":
                // 发送模板
//                weChatService.sendTemplate();
                break;
            case "unsubscribe":
                // 发送模板

                break;
            default:
        }

    }

//    public WeChatUnionId getWeChatUnionId(String accessToken, String openId) throws IOException {
//
//        HttpClient httpClient = HttpClientBuilder.create().build();
//        Map<String, String> requestMap = new HashMap<>();
//        requestMap.put("access_token", accessToken);
//        requestMap.put("openid", openId);
//        requestMap.put("lang", "zh_CN");
//        StringBuilder requestParams = new StringBuilder("?");
//        requestMap.forEach((k, v) -> requestParams.append(k).append("=").append(v).append("&"));
//        return JSONObject.parseObject(
//                EntityUtils.toString(
//                        httpClient.execute(new HttpGet("https://api.weixin.qq.com/cgi-bin/user/info" + requestParams)).getEntity()), WeChatUnionId.class);
//    }

}
