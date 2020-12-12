package com.isxcode.oxygen.flysql.response;

import com.isxcode.oxygen.flysql.common.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * success response advice
 *
 * @author ispong
 * @since 0.0.2
 */
@Aspect
@Component
@Slf4j
public class SuccessResponseAdvice {

    @Pointcut("@annotation(com.isxcode.oxygen.flysql.response.SuccessResponse)")
    public void operateLog() {
    }

    /**
     * has no return response
     *
     * @param
     * @return
     * @since 0.0.1
     */
    @After(value = "operateLog()&&@annotation(successResponse)")
    public void after(JoinPoint joinPoint, SuccessResponse successResponse) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String returnClassName = signature.getReturnType().getName();
        if ("void".equals(returnClassName)) {
            BaseResponse<Object> baseResponse = new BaseResponse<>();
            baseResponse.setCode(ResponseConstant.SUCCESS_CODE);
            baseResponse.setMsg(successResponse.value().isEmpty() ? successResponse.msg() : successResponse.value());
            successResponse(baseResponse);
        }
    }

    /**
     * has return response
     *
     * @param
     * @return
     * @since 0.0.1
     */
    @AfterReturning(returning = "data", value = "operateLog()&&@annotation(successResponse)")
    public void afterReturning(Object data, SuccessResponse successResponse) {

        BaseResponse<Object> baseResponse = new BaseResponse<>();
        baseResponse.setCode(ResponseConstant.SUCCESS_CODE);
        baseResponse.setData(data);
        baseResponse.setMsg(successResponse.value().isEmpty() ? successResponse.msg() : successResponse.value());
        successResponse(baseResponse);

    }

    /**
     * all exception
     *
     * @param
     * @return
     * @since 0.0.1
     */
    @AfterThrowing(throwing = "exception", value = "operateLog()&& @annotation(successResponse)")
    public void afterThrowing(Exception exception, SuccessResponse successResponse) {

        BaseResponse<Object> baseResponse = new BaseResponse<>();
        baseResponse.setCode("50010");
        baseResponse.setData("");
        baseResponse.setMsg(exception.getMessage());
        successResponse(baseResponse);
    }

    public void successResponse(BaseResponse<Object> baseResponse) {

        throw new SuccessException(baseResponse);
    }

}
