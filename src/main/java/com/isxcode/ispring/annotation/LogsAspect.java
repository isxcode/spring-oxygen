package com.isxcode.ispring.annotation;

import com.alibaba.fastjson.JSON;
import com.isxcode.ispring.model.entity.LogEntity;
import com.isxcode.ispring.repositories.LogRepository;
import com.isxcode.ispring.utils.AnnotationUtils;
import com.isxcode.ispring.utils.GeneratorUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Logs-注解解析器
 *
 * @author ispong
 * @date 2019/10/21
 * @version v0.1.0
 */
@Slf4j
@Aspect
@Component
public class LogsAspect {

    private final LogRepository logRepository;

    private final LogEntity logEntity;

    @Autowired
    public LogsAspect(LogEntity logEntity, LogRepository logRepository) {

        this.logRepository = logRepository;
        this.logEntity = logEntity;
    }

    /**
     * 监听所有Class-Logs注解
     */
    @Pointcut("@within(com.isxcode.ispring.annotation.Logs)")
    public void pointcut() {}

    /**
     * PostMapping - before
     *
     * @since 2019-11-16
     */
    @Before(value = "pointcut()&&@target(logs)&&@within(requestMapping)&&@annotation(postMapping)", argNames = "joinPoint,logs,requestMapping,postMapping")
    public void beforePost(JoinPoint joinPoint, Logs logs, RequestMapping requestMapping, PostMapping postMapping) {

        if (AnnotationUtils.checkExclude(logs.exclude(), joinPoint.getSignature().getName())) {
            GeneratorUtils.generateEntity(logEntity);
            logEntity.setApiName((Arrays.toString(requestMapping.value()) + Arrays.toString(postMapping.value())).replace("[", "").replace("]", ""));
            logEntity.setRequestParams(JSON.toJSONString(joinPoint.getArgs()));
            logEntity.setStartDate(LocalDateTime.now());
            logEntity.setExecuteTime(System.currentTimeMillis());
        }
    }

    /**
     * GetMapping - before
     *
     * @since 2019-11-16
     */
    @Before(value = "pointcut()&&@target(logs)&&@within(requestMapping)&&@annotation(getMapping)", argNames = "joinPoint,logs,requestMapping,getMapping")
    public void beforeGet(JoinPoint joinPoint, Logs logs, RequestMapping requestMapping, GetMapping getMapping) {

        if (AnnotationUtils.checkExclude(logs.exclude(), joinPoint.getSignature().getName())) {
            GeneratorUtils.generateEntity(logEntity);
            logEntity.setApiName((Arrays.toString(requestMapping.value()) + Arrays.toString(getMapping.value())).replace("[", "").replace("]", ""));
            logEntity.setRequestParams(JSON.toJSONString(joinPoint.getArgs()));
            logEntity.setStartDate(LocalDateTime.now());
            logEntity.setExecuteTime(System.currentTimeMillis());
        }
    }

    /**
     * ResponseEntity - afterReturning
     *
     * @since 2019-11-16
     */
    @AfterReturning(returning = "response",pointcut = "pointcut()")
    public void afterReturning(ResponseEntity response){

        if (logEntity.getApiName() != null) {
            logEntity.setResponseParams(JSON.toJSONString(response.getBody()));
            logEntity.setExecuteTime(System.currentTimeMillis() - logEntity.getExecuteTime());
            logRepository.save(logEntity);
        }
    }
}

