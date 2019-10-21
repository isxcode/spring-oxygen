package com.isxcode.isxcodespring.annotation;

import com.isxcode.isxcodespring.model.entity.LogEntity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @Autowired
    public LogAdvice(LogEntity logEntity) {
        this.logEntity = logEntity;
    }

    @Pointcut("@annotation(com.isxcode.isxcodespring.annotation.Log)")
    public void operateLog() {}

    @Before("operateLog()")
    public void before(JoinPoint joinPoint){
        System.out.println("执行方法前的操作"+logEntity);
        System.out.println(joinPoint);
    }

    @After("operateLog()")
    public void after(JoinPoint joinPoint){
        System.out.println("执行方法后的操作"+logEntity);
        System.out.println(joinPoint);
    }
}
