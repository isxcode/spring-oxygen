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
package com.ispong.oxygen.wechatgo.controller;

import com.ispong.oxygen.core.xml.XmlUtils;
import com.ispong.oxygen.wechatgo.exception.WechatgoException;
import com.ispong.oxygen.wechatgo.pojo.entity.WeChatEventBody;
import com.ispong.oxygen.wechatgo.service.WechatgoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 微信服务器调用接口
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
@ResponseBody
@RequestMapping("wechatgo")
public class WechatgoController {

    @Resource
    private WechatgoService wechatgoService;

    /**
     * 微信服务器认证接口
     *
     * @param echostr   echostr
     * @param timestamp timestamp
     * @param nonce     nonce
     * @param signature signature
     * @return echostr
     * @since 0.0.1
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
     * 接受微信回调函数接口
     *
     * @param httpServletRequest 请求体,为了获取微信推送的xml内容
     * @return 返回给微信服务器
     * @since 0.0.1
     */
    @PostMapping("/wechatServer")
    public String weChatListen(HttpServletRequest httpServletRequest) {

        log.debug("receive wechat event");
        try {
            WeChatEventBody weChatEventBody = XmlUtils.parseInputStreamXml(httpServletRequest.getInputStream(), WeChatEventBody.class);
            return wechatgoService.handlerWechatEvent(weChatEventBody);
        } catch (IOException e) {
            throw new WechatgoException("has no inputStream");
        }

    }
}
