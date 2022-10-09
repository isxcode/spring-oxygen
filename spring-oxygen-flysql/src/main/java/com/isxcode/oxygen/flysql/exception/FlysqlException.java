package com.isxcode.oxygen.flysql.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * flysql exception
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
public class FlysqlException extends RuntimeException {

	public FlysqlException(String message) {

		super("[oxygen-flysql]==> " + message);
	}
}
