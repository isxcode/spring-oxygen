package com.isxcode.oxygen.freecode.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.List;

/**
 * freecode properties
 *
 * @author ispong
 * @since 0.0.1
 */
@Data
@ConfigurationProperties("oxygen.freecode")
public class FreecodeProperties {

    /**
     * file type
     */
    private List<String> fileTypes = Arrays.asList("controller", "entity", "service", "repository", "innerService");

    /**
     * ignore columns
     */
    private List<String> ignoreColumns;

    /**
     * module path (src/main/java/com/)
     */
    private String modulePath;

    /**
     * author
     */
    private String author;

    /**
     * version
     */
    private String version = "v0.0.1";

    /**
     * base entity
     */
    private String baseEntityClass = "com.isxcode.oxygen.flysql.common.BaseEntity";

    /**
     * table prefix
     */
    private String tablePrefix;

    /**
     * template prefix
     */
    private String templatePrefix = ".java.ftl";
}
