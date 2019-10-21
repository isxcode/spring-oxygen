package com.isxcode.isxcodespring.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * BaseController
 *
 * @author ispong
 * @date 2019/10/16
 * @version v0.1.0
 */
public abstract class BaseController<T> {

    /**
     * 成功返回
     *
     * @param message message
     * @param data 返回数据体
     * @return 返回BaseResponse
     * @since 2019/10/16
     */
    public ResponseEntity<BaseResponse> successResponse(String message,Object data){

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(String.valueOf(HttpStatus.OK.value()));
        baseResponse.setMessage(message);
        baseResponse.setData(data);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

}