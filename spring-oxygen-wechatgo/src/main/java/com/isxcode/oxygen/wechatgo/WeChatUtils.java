package com.isxcode.oxygen.wechatgo;

import com.isxcode.oxygen.core.utils.XmlUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * wechat utils
 *
 * @author ispong
 * @version v0.1.0
 * @date 2020-01-28
 */
public class WeChatUtils {

    public static <T> T parseWeChatXml(HttpServletRequest httpServletRequest, Class<T> clazz) {

        try {
            return XmlUtils.parseXml(httpServletRequest.getInputStream(), clazz);
        } catch (IOException e) {
            throw new WeChatException("没有传入流");
        }

    }



}
