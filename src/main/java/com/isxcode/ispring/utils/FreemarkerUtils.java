package com.isxcode.ispring.utils;

import com.isxcode.ispring.TableColumn;
import com.isxcode.ispring.exception.IsxcodeException;
import com.isxcode.ispring.jdbc.SqlFactory;
import com.isxcode.ispring.model.dto.UserDto;
import com.isxcode.ispring.properties.CodeGenerateProperties;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static antlr.build.ANTLR.root;

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

    private static CodeGenerateProperties codeGenerateProperties;

    @Autowired
    public FreemarkerUtils(FreeMarkerConfigurer freeMarkerConfigurer, CodeGenerateProperties codeGenerateProperties) {

        FreemarkerUtils.freeMarkerConfigurer = freeMarkerConfigurer;
        FreemarkerUtils.codeGenerateProperties = codeGenerateProperties;
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
     * 生成java源代码
     *
     * @param
     * @return
     * @since 2020-01-07
     */
    public static void generateCode(String tableName) throws Exception {

        Map<String, Object> fileConfig = getFileConfig(tableName);

        // 遍历所有templates文件夹
        Stream<Path> templateFiles = Files.list(Paths.get(ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX + codeGenerateProperties.getTemplatesPath()).toString().replace("file:/", "")));
        templateFiles.forEach(templateFile -> {
            try {
                generateFile(templateFile.getFileName().toString(), fileConfig);
            } catch (Exception e) {
                throw new IsxcodeException("文件生成失败");
            }
        });
    }

    public static void generateFile(String templateName, Map<String, Object> fileConfig) throws Exception {


        String fileNameOld = templateName.replace(codeGenerateProperties.getTemplateSuffix(), "");
        String fileType = fileNameOld.replace(".java", "");
        String fileName = getUpStr(String.valueOf(fileConfig.get("tableName"))) + getUpStr(fileType);

        fileConfig.put("fileName", fileName);
        fileConfig.put("package", codeGenerateProperties.getPath() + "." + fileType);

        Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
        if (!Files.exists(Paths.get(codeGenerateProperties.getProjectPath() + fileType))) {
            Files.createDirectory(Paths.get(codeGenerateProperties.getProjectPath() + fileType));
        }
        if (!Files.exists(Paths.get(codeGenerateProperties.getProjectPath() + fileType + "/" + fileName + ".java"))) {
            Path file = Files.createFile(Paths.get(codeGenerateProperties.getProjectPath() + fileType + "/" + fileName + ".java"));
            String fileContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, fileConfig);
            Files.writeString(file, fileContent);
        }

    }

    public static Map<String, Object> getFileConfig(String tableName) {

        Map<String, Object> result = new HashMap<>();

        result.put("tableName", tableName);
        result.put("tableColumns", SqlFactory.selectSql(TableColumn.class).sql("SHOW FULL COLUMNS FROM " + tableName).query());


        return result;
    }

    public static String getUpStr(String str){
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

}
