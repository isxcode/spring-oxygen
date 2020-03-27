package com.ispong.oxygen.module.log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ispong.oxygen.module.file.FileEntity;
import com.ispong.oxygen.module.file.FileService;
import lombok.SneakyThrows;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@Aspect
@Component
public class LogsAdvice {

    private final LogService logService;

    private final LogEntity logEntity;

    private final FileService fileService;

    @Autowired
    public LogsAdvice(LogService logService, LogEntity logEntity, FileService fileService) {

        this.fileService = fileService;
        this.logEntity = logEntity;
        this.logService = logService;
    }

    @Pointcut("@within(com.ispong.oxygen.module.log.Logs)")
    public void operateLog() {

    }

    private void writeRequestLog(JoinPoint joinPoint, Logs logs, RequestMapping requestMapping, String postMapping) throws IOException {

        String apiName = joinPoint.getSignature().getName();
        if (!Arrays.asList(logs.excludes()).contains(apiName)) {
            logEntity.setLogId(UUID.randomUUID().toString());
            logEntity.setApiName(requestMapping.value()[0] + postMapping);
            logEntity.setExecuteDate(LocalDateTime.now());
            logEntity.setExecuteTime(System.currentTimeMillis());

            if ("StandardMultipartFile".equals(joinPoint.getArgs()[0].getClass().getSimpleName())) {

                logEntity.setRequestBody(((MultipartFile) joinPoint.getArgs()[0]).getOriginalFilename());
                // base64文件加密
                // logEntity.setRequestBody(Base64.getEncoder().encodeToString(((MultipartFile) joinPoint.getArgs()[0]).getBytes()));
            } else {
                logEntity.setRequestBody(new ObjectMapper().writeValueAsString(joinPoint.getArgs()[0]));
            }
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
            if ("UrlResource".equals(Objects.requireNonNull(response.getBody()).getClass().getSimpleName())) {
                FileEntity fileEntity = fileService.getFileEntity(((UrlResource) response.getBody()).getFilename());
                if (fileEntity != null) {
                    logEntity.setResponseBody(fileEntity.getFileName() + "." + fileEntity.getFileSuffix());
                }
                // base64加密文件
                // logEntity.setResponseBody(Base64.getEncoder().encodeToString(((UrlResource) response.getBody()).getInputStream().readAllBytes()));
            } else {
                logEntity.setResponseBody(new ObjectMapper().writeValueAsString(response.getBody()));
            }
            logEntity.setExecuteTime(System.currentTimeMillis() - logEntity.getExecuteTime());
            logService.saveLog(logEntity);
        }
    }


}
