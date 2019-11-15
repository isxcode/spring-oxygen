package com.isxcode.ispring.exception;

public class IsxcodeException extends RuntimeException {

    public IsxcodeException(String message) {
        super(message);
    }

    public IsxcodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
