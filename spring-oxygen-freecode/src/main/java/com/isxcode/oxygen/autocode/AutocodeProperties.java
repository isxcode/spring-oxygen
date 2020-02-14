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
package com.ispong.oxygen.autocode;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

/**
 * Autocode properties
 *
 * @author ispong
 * @version v0.1.0
 */
@Data
@ConfigurationProperties(prefix = "oxygen.autocode")
public class AutocodeProperties {

    public static String ENTITY = "entity";

    /**
     * 遍历浏览模板的地址里面的所有文件
     *
     * @since 2020-01-08
     */
    @PostConstruct
    public void scanTemplateFile() throws Exception {

        Stream<Path> templateFiles = Files.list(CodeUtils.getTemplatePath(templatesPath));
        templateFiles.forEach(templateFile -> templateFileList.put(CodeUtils.parseTemplateFileName(templateFile.getFileName().toString()), templateFile.getFileName().toString()));
    }

    /**
     * 模板列表信息
     * 记录<contorller,conroller.java.ftl>
     */
    private Map<String, String> templateFileList = new HashMap<>();

    /**
     * java包位置
     */
    private String mainPath = "src.main.java";

    /**
     * 模块路径
     */
    private String modulePath = "";

    /**
     * 生成文件的地址
     */
    private Map<String, String> filePaths = new HashMap<>();

    /**
     * 模板地址
     */
    private String templatesPath = "templates";

    /**
     * 模板后缀名
     */
    private String templateSuffix = ".ftl";

    /**
     * 作者
     */
    private String author;

    /**
     * 忽略字段
     */
    private List<String> ignoreFields = new ArrayList<>();

    /**
     * 初始化即将生成的类型
     */
    private List<String> fileTypeList = Arrays.asList("controller", "service", "entity", "dao");

    /**
     * Base类
     */
    private Map<String, String> baseClassList = new HashMap<>();
}
