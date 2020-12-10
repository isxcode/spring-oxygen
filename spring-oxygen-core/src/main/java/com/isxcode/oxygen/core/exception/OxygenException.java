package com.isxcode.oxygen.core.exception;

/**
 * Oxygen exception
 *
 * @author ispong
 * @since 0.0.1
 */
public class OxygenException extends RuntimeException {

    public OxygenException(String message) {

        super("[oxygen-core]==> " + message);
    }
}
