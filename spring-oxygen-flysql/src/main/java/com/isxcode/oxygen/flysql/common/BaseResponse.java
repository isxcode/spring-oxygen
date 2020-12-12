package com.isxcode.oxygen.flysql.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties
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
