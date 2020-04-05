package com.ispong.oxygen.freecode;

public class FreecodeException extends RuntimeException {

    public FreecodeException(String message) {
        super("[freecode]" + message);
    }
}
