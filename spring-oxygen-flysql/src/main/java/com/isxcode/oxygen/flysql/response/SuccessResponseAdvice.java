package com.isxcode.oxygen.flysql.response;

import com.isxcode.oxygen.flysql.common.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;

import java.io.InputStream;

/**
 * success response advice
 *
 * @author ispong
 * @since 0.0.2
 */
@Aspect
@Slf4j
public class SuccessResponseAdvice {

    @Pointcut("@annotation(com.isxcode.oxygen.flysql.response.SuccessResponse)")
    public void operateLog() {
    }

    /**
     * has return response
     *
     * @param data            data
     * @param successResponse successResponse
     * @param joinPoint       joinPoint
     * @since 0.0.1
     */
    @AfterReturning(returning = "data", value = "operateLog()&&@annotation(successResponse)")
    public void afterReturning(JoinPoint joinPoint, Object data, SuccessResponse successResponse) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        BaseResponse<Object> baseResponse = new BaseResponse<>();
        if (!"void".equals(signature.getReturnType().getName())) {
            if (data instanceof InputStream) {
                return;
            }
            baseResponse.setCode(ResponseConstant.SUCCESS_CODE);
            if (data.getClass().getDeclaredFields().length == 0) {
                baseResponse.setData(null);
            } else {
                baseResponse.setData(data);
            }
            baseResponse.setMsg(successResponse.value().isEmpty() ? successResponse.msg() : successResponse.value());
            successResponse(baseResponse);
        } else {
            baseResponse.setCode(ResponseConstant.SUCCESS_CODE);
            baseResponse.setMsg(successResponse.value().isEmpty() ? successResponse.msg() : successResponse.value());
            successResponse(baseResponse);
        }

    }

    public void successResponse(BaseResponse<Object> baseResponse) {

        throw new SuccessException(baseResponse);
    }

}
