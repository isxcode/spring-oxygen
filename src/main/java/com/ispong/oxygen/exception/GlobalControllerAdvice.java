package com.ispong.oxygen.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 全局异常捕获
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AuthException.class)
    @ResponseBody
    public ResponseEntity<String> authException(AuthException e) {

        return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
    }

}
