/*
 * Copyright [2020] [ispong]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ispong.oxygen.core.freemarker;

import com.ispong.oxygen.core.exception.OxygenException;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 * freemarker 模板生成服务
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
public class FreemarkerUtils {

    private static FreeMarkerConfigurer freeMarkerConfigurer;

    public FreemarkerUtils(FreeMarkerConfigurer freeMarkerConfigurer) {

        FreemarkerUtils.freeMarkerConfigurer = freeMarkerConfigurer;
    }

    /**
     * 通过模板生成对应文件
     *
     * @param templateName 模板名称
     * @param filePath     生成的文件地址+名称
     * @param params       freemarker需要解析的对象数据
     * @throws OxygenException 总异常
     * @since 0.0.1
     */
    public static void generateToFile(String templateName, Object params, String filePath) throws OxygenException {

        Path fileIoPath = Paths.get(filePath);

        if (Files.exists(fileIoPath)) {
            throw new OxygenException("file is exist");
        }

        try {
            Path path = Files.createFile(fileIoPath);
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
            Files.write(path, FreeMarkerTemplateUtils.processTemplateIntoString(template, params).getBytes());
        } catch (TemplateException | IOException ex) {
            log.debug(ex.getMessage());
            throw new OxygenException("freemarker make file is wrong");
        }
    }

    /**
     * freemarker 通过模板内容生成String的模板内容
     *
     * @param templateContent 模板内容
     * @param params          参数
     * @return 解析返回的字符串
     * @throws OxygenException 总异常
     * @since 0.0.1
     */
    public static String generateToString(String templateContent, Object params) throws OxygenException {

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_27);
        configuration.setTemplateLoader(new StringTemplateLoader());
        try {
            Template template = new Template("", templateContent, configuration);
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
        } catch (TemplateException | IOException e) {
            throw new OxygenException("freemarker parse template file to string is wrong");
        }
    }

    /**
     * freemarker 通过模板内容生成String的模板内容
     *
     * @param templateName 模板姓名
     * @param params       freemarker需要解析的对象数据
     * @return 解析返回的字符串
     * @throws OxygenException 总异常
     * @since 0.0.1
     */
    public static String fileToString(String templateName, Object params) throws OxygenException {

        try {
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
        } catch (TemplateException | IOException e) {
            throw new OxygenException("freemarker parse template file to string is wrong");
        }
    }

}
