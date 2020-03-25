package com.ispong.oxygen.exception;

/**
 * 身份认证异常
 *
 * @author ispong
 * @since 0.0.1
 */
public class AuthException extends RuntimeException {

    public AuthException(String message) {
        super("[auth exception]" + message);
    }
}
