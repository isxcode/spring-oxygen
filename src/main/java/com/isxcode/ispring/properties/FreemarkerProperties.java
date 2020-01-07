package com.isxcode.ispring.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 属性配置都得配置默认类
 *
 * @author ispon
 */
@Data
@Component
@ConfigurationProperties(prefix = "isxcode.freemarker")
public class FreemarkerProperties {

    /**
     * 开关
     */
    private String status = "";

    /**
     * 在resources的哪个文件夹中
     */
    private String templatesPath = "";

    /**
     * 作者
     */
    private String author;

    /**
     * 公司
     */
    private String company;

    /**
     * jdbc
     */
    private String jdbc;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 驱动
     */
    private String driver;

    /**
     * 项目系统上下文
     */
    private String projectContext;

    /**
     * 控制器的名字
     */
    private String controllerName;

    /**
     *
     */
    private String serviceName;

    /**
     * jpa
     */
    private String repositoryName;

    /**
     * entity
     */
    private String entityName;

}
