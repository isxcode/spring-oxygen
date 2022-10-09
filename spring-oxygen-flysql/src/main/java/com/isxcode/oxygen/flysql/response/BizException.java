package com.isxcode.oxygen.flysql.response;

/**
 * biz exception
 *
 * @author ispong
 * @since 0.0.2
 */
public class BizException extends AbstractException {

	public BizException(AbstractExceptionEnum abstractExceptionEnum) {

		super(abstractExceptionEnum);
	}

	public BizException(String code, String msg) {

		super(code, msg);
	}

	public BizException(String msg) {

		super(msg);
	}
}
