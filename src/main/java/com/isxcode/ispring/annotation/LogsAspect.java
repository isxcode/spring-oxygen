package com.isxcode.ispring.annotation;

import com.isxcode.ispring.model.entity.LogEntity;
import com.isxcode.ispring.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * 注解解析器- Log注解 AOP切面编程
 *
 * @author ispong
 * @date 2019/10/21
 * @version v0.1.0
 */
@Slf4j
@Aspect
@Component
public class LogsAspect {

    /**
     * 日志服务
     */
    private final LogService logService;

    /**
     * 日志对象
     */
    private final LogEntity logEntity;

    @Autowired
    public LogsAspect(LogEntity logEntity, LogService logService) {

        this.logService = logService;
        this.logEntity = logEntity;
    }

    /**
     * 指定AOP切面的地址
     */
    @Pointcut("@within(com.isxcode.ispring.annotation.Logs)")
    public void pointcut() {}

    @Pointcut("@annotation(com.isxcode.ispring.annotation.Logs)")
    public void pointcut1() {}

    /**
     * 在检测到连接点之前
     */
    @Before("pointcut1()&& @annotation(logs)")
    public void before(JoinPoint joinPoint, Logs logs) {

        log.info("检测到调用接口 before");
//        logEntity.setApiName(joinPoint.getSignature().getName());
//        logEntity.setRequestParams(JSON.toJSONString(joinPoint.getArgs()));
//        logEntity.setStartDate(LocalDateTime.now());
    }

    /**
     * 中间代理
     */
    @Around("pointcut1()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{

        log.info("中间处理方式 around");
        Object proceed = pjp.proceed();
        log.info("around 结束");
        return proceed;
    }

    /**
     * 当检测的service返回值时调用方法
     */
    @AfterReturning(returning = "response",pointcut = "pointcut()")
    public void afterReturning(ResponseEntity response){

//        logEntity.setResponseParams(JSON.toJSONString(response.getBody()));
//        logEntity.setEndDate(LocalDateTime.now());
//        logEntity.setCreateDate(LocalDateTime.now());
//        logService.save(logEntity);
        log.info("检测到返回参数 afterreturn");
    }

    /**
     * 当方法返回报异常时处理
     */
    @AfterThrowing("pointcut()")
    public void afterThrowing(){
        log.info("异常处理方式 atfer throw");
    }

    /**
     * 最终返回
     */
    @After("pointcut()&&args(test)&&@annotation(logs)")
    public void after(String test,Logs logs) {

        log.info("最终的处理方式 after" + test);
    }

}

