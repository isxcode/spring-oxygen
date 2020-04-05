package com.ispong.oxygen.freecode;

import freemarker.template.Template;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * freecode工具类
 *
 * @author ispong
 * @since 0.0.1
 */
public class FreecodeUtils {

    private static FreeMarkerConfigurer freeMarkerConfigurer;

    public FreecodeUtils(FreeMarkerConfigurer freeMarkerConfigurer) {

        FreecodeUtils.freeMarkerConfigurer = freeMarkerConfigurer;
    }

    /**
     * 生成文件
     *
     * @param fileName     文件名
     * @param templateName 模板名
     * @param freecodeInfo 对象
     * @param filePath     文件路径
     * @since 0.0.1
     */
    public static void generateFile(String filePath, String fileName, String templateName, Object freecodeInfo) {

        if (!Files.exists(Paths.get(filePath)) && !Files.isDirectory(Paths.get(filePath))) {
            throw new FreecodeException("路径不存在");
        }
        if (!Files.exists(Paths.get(filePath + fileName))) {
            try {
                Path path = Files.createFile(Paths.get(filePath));
                Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
                String s = FreeMarkerTemplateUtils.processTemplateIntoString(template, freecodeInfo);
                Files.writeString(path, s);
            } catch (Exception e) {
                throw new FreecodeException("文件生成失败");
            }
        }
    }
}
