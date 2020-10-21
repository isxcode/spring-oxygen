package com.ispong.oxygen.flysql.success;

import com.ispong.oxygen.flysql.common.BaseResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class SuccessResponseAdvice {

    @Pointcut("@annotation(com.ispong.oxygen.flysql.success.SuccessResponse)")
    public void operateLog() {
    }

    @SneakyThrows
    @After(value = "operateLog()&&@annotation(successResponse)")
    public void after(JoinPoint joinPoint, SuccessResponse successResponse) {

        Signature signature = joinPoint.getSignature();
        Class<?> declaringType = signature.getDeclaringType();
        String returnClassName = declaringType.getMethod(signature.getName()).getReturnType().getName();
        if ("void".equals(returnClassName)) {
            BaseResponse<Object> baseResponse = new BaseResponse<>();
            baseResponse.setCode("200");
            baseResponse.setData("");
            baseResponse.setMsg(successResponse.msg());
            successResponse(baseResponse);
        }
    }

    @AfterReturning(returning = "data", value = "operateLog()&&@annotation(successResponse)")
    public void afterReturning(Object data, SuccessResponse successResponse) {

        if (data != null) {
            BaseResponse<Object> baseResponse = new BaseResponse<>();
            baseResponse.setCode("200");
            baseResponse.setData(data);
            baseResponse.setMsg(successResponse.msg());
            successResponse(baseResponse);
        }

    }

    public void successResponse(BaseResponse<Object> baseResponse) {

        throw new SuccessException(baseResponse);
    }

}
