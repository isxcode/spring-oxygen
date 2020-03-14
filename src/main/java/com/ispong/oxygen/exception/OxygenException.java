package com.ispong.oxygen.exception;

public class OxygenException extends RuntimeException {

    public OxygenException(String message) {
        super("[oxygen:]" + message);
    }
}
