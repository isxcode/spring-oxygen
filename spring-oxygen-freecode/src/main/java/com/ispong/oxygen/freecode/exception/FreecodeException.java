package com.ispong.oxygen.freecode.exception;

public class FreecodeException extends RuntimeException {

    public FreecodeException(String message) {
        super("[freecode]" + message);
    }
}
