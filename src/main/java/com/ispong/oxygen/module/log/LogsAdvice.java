package com.ispong.oxygen.module.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
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
import java.util.UUID;

@Aspect
@Component
public class LogsAdvice {

    private final LogService logService;

    private final LogEntity logEntity;

    @Autowired
    public LogsAdvice(LogService logService, LogEntity logEntity) {

        this.logEntity = logEntity;
        this.logService = logService;
    }

    @Pointcut("@within(com.ispong.oxygen.module.log.Logs)")
    public void operateLog() {

    }

    private void writeRequestLog(JoinPoint joinPoint, Logs logs, RequestMapping requestMapping, String postMapping) throws JsonProcessingException {

        String apiName = joinPoint.getSignature().getName();
        if (!Arrays.asList(logs.excludes()).contains(apiName)) {
            logEntity.setLogId(UUID.randomUUID().toString());
            logEntity.setModuleName(requestMapping.value()[0]);
            logEntity.setApiName(postMapping);
            logEntity.setExecuteDate(LocalDateTime.now());
            logEntity.setExecuteTime(System.currentTimeMillis());
            logEntity.setRequestBody(new ObjectMapper().writeValueAsString(joinPoint.getArgs()[0]));
        }
    }

    @SneakyThrows
    @Before(value = "operateLog()&&@target(logs)&&@annotation(postMapping)&&@within(requestMapping)", argNames = "joinPoint,logs,postMapping,requestMapping")
    public void before(JoinPoint joinPoint, Logs logs, PostMapping postMapping, RequestMapping requestMapping) {

        writeRequestLog(joinPoint, logs, requestMapping, postMapping.value()[0]);
    }

    @SneakyThrows
    @Before(value = "operateLog()&&@target(logs)&&@within(requestMapping)&&@annotation(getMapping)", argNames = "joinPoint,logs,getMapping,requestMapping")
    public void before(JoinPoint joinPoint, Logs logs, GetMapping getMapping, RequestMapping requestMapping) {

        writeRequestLog(joinPoint, logs, requestMapping, getMapping.value()[0]);
    }

    @SneakyThrows
    @AfterReturning(returning = "response", pointcut = "operateLog()&&@target(logs)", argNames = "joinPoint,response,logs")
    public void afterReturning(JoinPoint joinPoint, ResponseEntity<?> response, Logs logs) {

        String apiName = joinPoint.getSignature().getName();
        if (!Arrays.asList(logs.excludes()).contains(apiName)) {
            logEntity.setResponseBody(new ObjectMapper().writeValueAsString(response.getBody()));
            logEntity.setExecuteTime(System.currentTimeMillis() - logEntity.getExecuteTime());
            logService.saveLog(logEntity);
        }
    }


}
