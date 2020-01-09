package com.isxcode.ispring.code;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
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
