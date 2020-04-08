package com.ispong.oxygen.freecode;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author ispong
 * @since 0.0.1
 */
@Data
@ConfigurationProperties("oxygen.freecode")
public class FreecodeProperties {

    /**
     * 文件类型
     */
    private List<String> fileTypes;

    /**
     * 忽略字段
     */
    private List<String> ignoreColumns;

    /**
     * 模块的地址
     */
    private String modulePath;

    /**
     * 作者
     */
    private String author;

    /**
     * 版本号
     */
    private String version = "0.0.1";

    /**
     * 基础类Entity
     */
    private String baseEntityClass;

    /**
     * 基础类Controller
     */
    private String baseControllerClass;

}
