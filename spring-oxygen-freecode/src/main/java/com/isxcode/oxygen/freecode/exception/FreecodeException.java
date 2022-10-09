package com.isxcode.oxygen.freecode.exception;

/**
 * freecode exception
 *
 * @author ispong
 * @since 0.0.1
 */
public class FreecodeException extends RuntimeException {

	public FreecodeException(String message) {

		super("[oxygen-freecode] " + message);
	}
}
