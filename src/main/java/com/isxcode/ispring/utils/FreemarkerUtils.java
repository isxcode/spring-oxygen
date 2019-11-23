package com.isxcode.ispring.utils;

import com.isxcode.ispring.properties.FreemarkerProperties;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties;
import org.springframework.util.ClassUtils;

import java.io.*;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ispon
 */
@Slf4j
public class FreemarkerUtils {

    private static FreemarkerProperties freemarkerProperties;

    public FreemarkerUtils(FreemarkerProperties freemarkerProperties){
        FreemarkerUtils.freemarkerProperties = freemarkerProperties;
    }

    public static void main(String[] args) {

        // 初始化对象
//        log.info(freeMarkerProperties.getTemplatesPath());
        // --- 官方配置 只能初始化一次
//        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
//        cfg.setDirectoryForTemplateLoading(new File(templatesPath));
//        cfg.setDefaultEncoding("UTF-8");
//        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
//        cfg.setLogTemplateExceptions(false);
//        cfg.setWrapUncheckedExceptions(true);
//        cfg.setFallbackOnNullLoopVariable(false);
//
//        // 自己构建的参数对象
//
//        Map root = new HashMap<String, String>(2);
//        root.put("user", "Big Joe");
//
//        // 指定模板指定输出换环境
//        Template temp = cfg.getTemplate("test.ftlh");
//        OutputStream outputStream = new FileOutputStream(new File("D:\\templates"));
//        Writer out = new OutputStreamWriter(outputStream);
//        temp.process(root, out);
    }

}
