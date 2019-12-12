package com.isxcode.ispring.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * BaseController
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019/10/16
 */
public abstract class BaseController {

    /**
     * 成功返回
     *
     * @param message message
     * @param data    返回数据体
     * @return 返回BaseResponse
     * @since 2019/10/16
     */
    protected ResponseEntity<BaseResponse> successResponse(String message, Object data) {

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(String.valueOf(HttpStatus.OK.value()));
        baseResponse.setMessage(message);
        baseResponse.setData(data);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    /**
     * 获取用户的id
     *
     * @since 2019-12-11
     */
    protected String getUserId(){

        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

}