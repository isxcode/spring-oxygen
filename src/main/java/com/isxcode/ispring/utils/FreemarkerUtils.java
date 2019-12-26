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
 * 生成代码工具
 * ( controller  service  entity  dto repositories )
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019/10/17
 */
@Slf4j
@Component
public class FreemarkerUtils {

    private static FreeMarkerConfigurer freeMarkerConfigurer;

    @Autowired
    public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
        FreemarkerUtils.freeMarkerConfigurer = freeMarkerConfigurer;
    }

    /**
     * 通过freemarker模板获取html邮件的String
     *
     * @param map          参数集合
     * @param templateFile 模板文件
     * @return Email邮件的String
     * @since 2019-11-28
     */
    public static String getEmailHtmlContent(Map<String, String> map, String templateFile) {

        try {
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateFile);
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        } catch (IOException | TemplateException e) {
            throw new IsxcodeException("无法解析freemarker");
        }
    }


    /**
     * freemarker生成器
     *
     * 1- 指定数据库  通过数据库名称  指定文件夹   生成Entity Controller
     *
     * @since 2019-12-20
     */
//    public static void main(String[] args) throws Exception {
//
//        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
//        cfg.setDirectoryForTemplateLoading(new File(""));
//        cfg.setDefaultEncoding("UTF-8");
//        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
//        cfg.setLogTemplateExceptions(false);
//        cfg.setWrapUncheckedExceptions(true);
//        cfg.setFallbackOnNullLoopVariable(false);
//
//        //
//
//        // 指定模板指定输出换环境3
//        Template temp = cfg.getTemplate("test.ftlh");
//        OutputStream outputStream = new FileOutputStream(new File("D:\\templates"));
//        Writer out = new OutputStreamWriter(outputStream);
//        temp.process(root, out);
//    }

}
