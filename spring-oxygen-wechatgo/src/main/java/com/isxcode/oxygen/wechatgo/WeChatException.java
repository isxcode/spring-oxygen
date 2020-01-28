package com.isxcode.oxygen.wechatgo;

/**
 * 异常处理类
 *
 * @author ispong
 * @version v0.1.0
 * @date 2020-01-28
 */
public class WeChatException extends RuntimeException {

    public WeChatException(String message) {
        super(message);
    }
}
