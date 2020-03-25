package com.ispong.oxygen.exception;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 将filter的异常,转移到controller
 *
 * @author ispong
 * @since 0.0.1
 */
@RestController
public class ExceptionController {

    /**
     * ispong
     * token不存在
     *
     * @since 7/5/2019
     */
    @RequestMapping("/userAuthException")
    public void userAuthException(AuthException e) {

        throw new AuthException(e.getMessage());
    }
}
