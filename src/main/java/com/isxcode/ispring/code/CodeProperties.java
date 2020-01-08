package com.isxcode.ispring.code;

import com.isxcode.ispring.utils.PathUtils;
import lombok.Data;
import lombok.NonNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * 自动生成代码工具类
 *
 * @author ispong
 * @version v0.1.0
 * @date 2020-01-07
 */
@Data
@Component
@ConfigurationProperties(prefix = "isxcode.code-generate")
public class CodeProperties {

    /**
     * 初始化浏览模板的地址里面的所有文件
     *
     * @since 2020-01-08
     */
    @PostConstruct
    public void scanTemplateFile() {

        try {
            Stream<Path> templateFiles = Files.list(CodeUtils.getTemplatePath());
            templateFiles.forEach(templateFile -> templateFileList.put(CodeUtils.parseTemplateFileName(templateFile.getFileName().toString()), templateFile.getFileName().toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 模板列表信息
     */
    private Map<String, String> templateFileList = new HashMap<>();

    /**
     * main路径
     */
    private static final String MAIN_PATH = "src/main/";

    /**
     * resources路径
     */
    private static final String RESOURCES_PATH = "src/resources/";

    private String templateSuffix = ".ftl";

    /**
     * controller地址
     */
    private String controllerPath = "";

    /**
     * service地址
     */
    private String servicePath = "";

    /**
     * entity地址
     */
    private String entityPath = "";

    /**
     * dao地址
     */
    private String daoPath = "";

    /**
     * com路径
     */
    private String projectPath = "com.isxcode.ispring";

    /**
     * 模板地址
     */
    private String templatesPath = "templates";



    private Map<String, String> paths = new HashMap<>();

}
