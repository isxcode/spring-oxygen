package com.isxcode.oxygen.flysql.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * base response
 *
 * @author ispong
 * @version v0.1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {

    /**
     * code
     */
    private String code;

    /**
     * msg
     */
    private String msg;

    /**
     * data
     */
    private T data;
}
