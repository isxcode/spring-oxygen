package com.isxcode.oxygen.common;

//import com.isxcode.ispring.security.UserSecurityDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;

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
//    protected UserSecurityDetail getUserInfo() {
//
//        // SecurityContextHolder 获取上下文 使用ThreadLocal 存储用户信息
//        return ((UserSecurityDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//
//    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//if (principal instanceof UserDetails) {
//        String username = ((UserDetails)principal).getUsername();
//    } else {
//        String username = principal.toString();
//    }
//    }

}