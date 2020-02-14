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

import com.ispong.oxygen.core.exception.UtilsException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;
import java.util.Map;

/**
 * Freemarker Utils
 *
 * @author ispong
 * @version v0.1.0
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
            throw new UtilsException("无法解析freemarker");
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

