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

import com.isxcode.oxygen.wechatgo.model.WeChatEventBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * wechat server api
 *
 * @author ispong
 * @version v0.1.0
 */
@Slf4j
@ResponseBody
@RequestMapping("wechatgo")
public class WechatgoController {

    private final WechatgoService wechatgoService;

    public WechatgoController(WechatgoService weChatService) {

        this.wechatgoService = weChatService;
    }

    /**
     * get接口-微信服务器认证
     *
     * @param echostr   微信的认证值
     * @param timestamp timestamp
     * @param nonce     nonce
     * @param signature signature
     * @return 返回微信的认证值
     * @since 2020-01-14
     */
    @GetMapping("/wechatServer")
    public String weChatAuthentication(
            @RequestParam("echostr") String echostr,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("nonce") String nonce,
            @RequestParam("signature") String signature) {

        log.debug("wechat go to auth");
        if (wechatgoService.checkWeChat(nonce, timestamp, signature)) {
            return echostr;
        }
        throw new WechatgoException("not wechat request");
    }

    /**
     * post接口-接受微信事件(和get接口地址保持一直)
     *
     * @param httpServletRequest servlet请求体
     * @since 2020-01-14
     */
    @PostMapping("/wechatServer")
    public void weChatListen(HttpServletRequest httpServletRequest) {

        log.debug("receive wechat event");
        WeChatEventBody weChatEventBody = WechatgoUtils.parseWeChatXml(httpServletRequest, WeChatEventBody.class);
        wechatgoService.handlerWechatEvent(weChatEventBody);
    }
}
