package com.ispong.oxygen.exception;

import com.ispong.oxygen.flysql.common.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 全局异常捕获
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
@ControllerAdvice
@ResponseBody
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ObjectError objectError = ex.getBindingResult().getAllErrors().get(0);
        return new ResponseEntity<>(new BaseResponse<>("400", objectError.getDefaultMessage(), ""), HttpStatus.OK);
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<String> authException(AuthException e) {

        return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
    }

}
