package com.isxcode.ispring.utils;

import com.isxcode.ispring.exception.IsxcodeException;
import com.isxcode.ispring.code.CodeProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 关于文件处理的工具类
 *
 * @author ispong
 * @version v0.1.0
 * @date 2020-01-08
 */
@Slf4j
@Component
public class PathUtils {

    private final static String JAVA_PATH = "src/main/java/";

    public static Path parsePropertiesToPath(String propertiesPath,String fileType){

        return Paths.get(JAVA_PATH + propertiesPath.replace(".", "/") + fileType);
    }

}
