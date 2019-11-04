package com.isxcode.isxcodespring.common;

import lombok.Data;

/**
 * BaseResponse
 *
 * @author ispong
 * @date 2019/10/17
 * @version v0.1.0
 */
@Data
public class BaseResponse {

    /**
     * 返回的code
     */
    private String code;

    /**
     * 返回的message
     */
    private String message;

    /**
     * 返回体数据
     */
    private Object data;
}
