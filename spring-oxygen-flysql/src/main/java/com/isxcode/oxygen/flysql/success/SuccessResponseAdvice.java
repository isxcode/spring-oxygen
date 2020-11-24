package com.isxcode.oxygen.flysql.success;

import com.isxcode.oxygen.flysql.common.BaseResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class SuccessResponseAdvice {

    @Pointcut("@annotation(com.isxcode.oxygen.flysql.success.SuccessResponse)")
    public void operateLog() {
    }

    @SneakyThrows
    @After(value = "operateLog()&&@annotation(successResponse)")
    public void after(JoinPoint joinPoint, SuccessResponse successResponse) {

//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        String returnClassName = signature.getReturnType().getName();
//        if ("void".equals(returnClassName)) {
//        }
        if (successResponse.isNull()) {
            BaseResponse<Object> baseResponse = new BaseResponse<>();
            baseResponse.setCode("200");
            baseResponse.setData("");
            baseResponse.setMsg(successResponse.value().isEmpty() ? successResponse.msg() : successResponse.value());
            successResponse(baseResponse);
        }
    }

    @AfterReturning(returning = "data", value = "operateLog()&&@annotation(successResponse)")
    public void afterReturning(Object data, SuccessResponse successResponse) {

        BaseResponse<Object> baseResponse = new BaseResponse<>();
        baseResponse.setCode("200");
        baseResponse.setData(data == null ? "" : data);
        baseResponse.setMsg(successResponse.value().isEmpty() ? successResponse.msg() : successResponse.value());
        successResponse(baseResponse);

    }

//    @AfterThrowing(throwing = "exception", value = "operateLog()&& @annotation(successResponse)")
//    public void afterThrowing(Exception exception, SuccessResponse successResponse) {
//
//        BaseResponse<Object> baseResponse = new BaseResponse<>();
//        baseResponse.setCode("50010");
//        baseResponse.setData("");
//        baseResponse.setMsg(exception.getMessage());
//        successResponse(baseResponse);
//    }

    public void successResponse(BaseResponse<Object> baseResponse) {

        throw new SuccessException(baseResponse);
    }

}
