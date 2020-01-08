package com.isxcode.ispring.code;

import com.isxcode.ispring.exception.IsxcodeException;
import com.isxcode.ispring.utils.FreemarkerUtils;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 自动生成代码工具类
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019/10/20
 */
public class CodeUtils {

    /**
     * 自动生成代码
     *
     * @param codeInfo 自动生成代码内容
     * @since 2020-01-08
     */
    public static void generateFile(CodeInfo codeInfo) {

        try {
            // 生成文件夹
            if (!Files.exists(Paths.get(codeInfo.getDirectoryPath()))) {
                Files.createDirectories(Paths.get(codeInfo.getDirectoryPath()));
            }
            // 生成文件
            if (!Files.exists(Paths.get(codeInfo.getFilePath()))) {
                Path path = Files.createFile(Paths.get(codeInfo.getFilePath()));
                String codeContent = FreemarkerUtils.getFreemarkerStr(codeInfo.getTemplateName(), codeInfo);
                Files.writeString(path, codeContent);
            }
        } catch (Exception e) {
            throw new IsxcodeException("文件生成失败");
        }

    }

    /**
     * 将controller.java.ftl 转换成controller
     *
     * @param templateFileName 模板名称
     * @since 2020-01-08
     */
    public static String parseTemplateFileName(String templateFileName) {

        return templateFileName.split("\\.")[0];
    }

    /**
     * 将controller.java.ftl 转换成java
     *
     * @param templateFileName 模板名称
     * @since 2020-01-08
     */
    public static String parseTemplateFileType(String templateFileName) {

        return templateFileName.split("\\.")[1];
    }

    /**
     * 通过配置文件生成模板地址
     *
     * @since 2020-01-08
     */
    public static Path getTemplatePath() {

        try {
            return Paths.get(ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX + "templates").toString().replace("file:/", ""));
        } catch (FileNotFoundException e) {
            throw new IsxcodeException("无法获取resources路径");
        }
    }

}
