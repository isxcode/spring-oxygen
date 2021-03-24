package com.isxcode.oxygen.flysql.response;

import lombok.Getter;

/**
 * Abstract Exception Template
 *
 * @author ispong
 * @since 0.0.1
 */
public abstract class AbstractException extends RuntimeException {

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

    public AbstractException(String code, String msg) {

        this.code = code;
        this.msg = msg;
    }

    public AbstractException(String msg) {

        this.code = null;
        this.msg = msg;
    }
}
