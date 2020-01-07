package com.isxcode.ispring.properties;

import lombok.Data;
import lombok.NonNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;

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
public class CodeGenerateProperties {

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
     * com路径
     */
    @NonNull
    private String projectPath = "com.isxcode.ispring";

    /**
     * 模板地址
     */
    @NonNull
    private String templatesPath = "templates";

    public String getProjectPath() {

        return MAIN_PATH + "java/" + projectPath.replace(".", "/") + "/";
    }

    public String getPath() {
        return projectPath;
    }
}
