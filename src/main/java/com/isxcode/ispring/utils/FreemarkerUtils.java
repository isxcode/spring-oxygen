package com.isxcode.ispring.utils;

import com.isxcode.ispring.exception.IsxcodeException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.*;
import java.util.Map;

/**
 * freemarker 工具类
 * <p>
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
    public FreemarkerUtils(FreeMarkerConfigurer freeMarkerConfigurer) {

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
     * 获取String
     *
     * @since 2020-01-08
     */
    public static String getFreemarkerStr(String templateFileName, Object model) throws IOException, TemplateException {

        Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateFileName);
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
    }

}
