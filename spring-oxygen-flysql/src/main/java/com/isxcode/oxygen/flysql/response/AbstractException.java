package com.isxcode.oxygen.flysql.response;

import lombok.Getter;

/**
 * AbstractException
 *
 * @author ispong
 * @since 0.0.1
 */
abstract class AbstractException extends RuntimeException {

    /**
     * code
     */
    @Getter
    private final String code;

    /**
     * msg
     */
    @Getter
    private final String msg;

    public AbstractException(AbstractExceptionEnum abstractExceptionEnum) {

        this.code = abstractExceptionEnum.getCode();
        this.msg = abstractExceptionEnum.getMsg();
    }

}
