package com.isxcode.isxcodespring.annotation;

import com.alibaba.fastjson.JSON;
import com.isxcode.isxcodespring.model.entity.LogEntity;
import com.isxcode.isxcodespring.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 注解解析器- Log注解
 *
 * @author ispong
 * @date 2019/10/21
 * @version v0.1.0
 */
@Aspect
@Component
public class LogAdvice {

    private final LogEntity logEntity;

    private final LogService logService;

    @Autowired
    public LogAdvice(LogEntity logEntity, LogService logService) {

        this.logService = logService;
        this.logEntity = logEntity;
    }

    @Pointcut("@annotation(com.isxcode.isxcodespring.annotation.Log)")
    public void operateLog() {}

    @Before("operateLog()")
    public void before(JoinPoint joinPoint){

        logEntity.setApiName(joinPoint.getSignature().getName());
        logEntity.setRequestParams(JSON.toJSONString(joinPoint.getArgs()));
        logEntity.setStartDate(LocalDateTime.now());
    }

    @AfterReturning(returning = "response",pointcut = "operateLog()")
    public void after(ResponseEntity response){

        logEntity.setResponseParams(JSON.toJSONString(response.getBody()));
        logEntity.setEndDate(LocalDateTime.now());
        logEntity.setCreateDate(LocalDateTime.now());
        logService.save(logEntity);
    }
}
