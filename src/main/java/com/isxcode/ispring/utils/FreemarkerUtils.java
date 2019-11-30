package com.isxcode.ispring.utils;

import com.isxcode.ispring.exception.IsxcodeException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * freemarker 工具类
 *
 * @author ispon
 */
@Slf4j
@Component
public class FreemarkerUtils {

    private static FreeMarkerConfigurer freeMarkerConfigurer;

    @Autowired
    public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer){
        FreemarkerUtils.freeMarkerConfigurer = freeMarkerConfigurer;
    }

    /**
     * 通过freemarker模板获取html邮件的String
     *
     * @param map 参数集合
     * @param templateFile 模板文件
     * @return Email邮件的String
     * @since 2019-11-28
     */
    public static String getEmailHtmlContent(Map<String,String> map,String templateFile){

        try {
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateFile);
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
            throw new IsxcodeException("无法解析freemarker");
        }
    }

    public static void main(String[] args) throws Exception {

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
        cfg.setDirectoryForTemplateLoading(new File(""));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);

        // 自己构建的参数对象

        Map root = new HashMap<String, String>(2);
        root.put("user", "Big Joe");

        // 指定模板指定输出换环境
        Template temp = cfg.getTemplate("test.ftlh");
        OutputStream outputStream = new FileOutputStream(new File("D:\\templates"));
        Writer out = new OutputStreamWriter(outputStream);
        temp.process(root, out);
    }

}
