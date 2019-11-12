package com.isxcode.isxcodespring.annotation;

import com.isxcode.isxcodespring.model.entity.LogEntity;
import com.isxcode.isxcodespring.service.LogService;
import com.isxcode.isxcodespring.utils.AnnotationUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@Slf4j
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
     *
     */
    @Pointcut("@within(com.isxcode.isxcodespring.annotation.Logs)")
    public void pointcut() {}

    /**
     * 在检测到连接点之前
     */
    @Before("pointcut()&& @target(logs)")
    public void before(JoinPoint joinPoint, Logs logs) {

        if (AnnotationUtils.checkExclude(logs.exclude(), joinPoint.getSignature().getName())) {

            log.info("检测到调用接口" + logs.exclude());
//        logEntity.setApiName(joinPoint.getSignature().getName());
//        logEntity.setRequestParams(JSON.toJSONString(joinPoint.getArgs()));
//        logEntity.setStartDate(LocalDateTime.now());
        }
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
        log.info("检测到返回参数");
    }


//    /**
//     * 当方法返回报异常时处理
//     */
//    @AfterThrowing("")
//    public void afterThrowing(){
//
//    }

//    /**
//     * 最终返回
//     */
//    @After("")
//    public void after() {
//
//    }

//    /**
//     * 中间代理
//     */
//    @Around("")
//    public Object around(ProceedingJoinPoint pjp){
//
//        return null;
//    }


}
