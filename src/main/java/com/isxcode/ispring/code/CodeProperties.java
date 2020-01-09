package com.isxcode.ispring.code;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * 自动生成代码的配置信息
 *
 * @author ispong
 * @version v0.1.0
 * @date 2020-01-07
 */
@Data
@Component
@ConfigurationProperties(prefix = "isxcode.code")
public class CodeProperties {

    /**
     * 初始化浏览模板的地址里面的所有文件
     *
     * @since 2020-01-08
     */
    @PostConstruct
    public void scanTemplateFile() {

        try {
            Stream<Path> templateFiles = Files.list(CodeUtils.getTemplatePath(templatesPath));
            templateFiles.forEach(templateFile -> templateFileList.put(CodeUtils.parseTemplateFileName(templateFile.getFileName().toString()), templateFile.getFileName().toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private String author;

    private List<String> ignoreFields = new ArrayList<>();
}
