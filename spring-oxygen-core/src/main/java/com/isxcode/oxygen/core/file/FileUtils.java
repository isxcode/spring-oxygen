package com.isxcode.oxygen.core.file;

import com.isxcode.oxygen.core.exception.OxygenException;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class FileUtils {

    /**
     * translate windows path to str
     *
     * @param pathStr pathStr
     */
    public static String translateWindowsPath(String pathStr) {

        return pathStr.contains("\\") ? pathStr.replace("\\", "\\\\") : pathStr;
    }

    /**
     * generate dirs
     *
     * @param dirPath dirPath
     */
    public static void generateDirs(String dirPath) {

        Path dir = Paths.get(translateWindowsPath(dirPath));
        if (!Files.exists(dir)) {
            try {
                Files.createDirectories(dir);
            } catch (IOException e) {
                throw new OxygenException("create dir path error");
            }
        }
    }

    /**
     * generate file
     *
     * @param filePath filePath
     * @return filePath
     */
    public static Path generateFile(String filePath) {

        // generate dir
        int endSeparator = filePath.lastIndexOf(File.separator);
        String dirPath = filePath.substring(0, endSeparator);
        generateDirs(dirPath);

        // generate file
        Path file = Paths.get(filePath);
        if (!Files.exists(file)) {
            try {
                return Files.createFile(file);
            } catch (IOException e) {
                throw new OxygenException("create file path error");
            }
        } else {
            return file;
        }
    }

    /**
     * write string to file or new file
     *
     * @param content strContent
     * @param dirPath dir
     * @param fileName fileName+suffix
     * @param options StandardOpenOption.WRITE/StandardOpenOption.APPEND
     */
    public static void StringToFile(String content, String dirPath, String fileName, OpenOption... options) {

        // generate filePath
        Path file = generateFile(dirPath + File.separator + fileName);

        // write string to file
        try {
            Files.write(file, content.getBytes(), options);
        } catch (IOException e) {
            throw new OxygenException("write content error");
        }
    }

    /**
     * write string to file or new file
     *
     * @param content  strContent
     * @param filePath file
     * @param options  StandardOpenOption.WRITE/StandardOpenOption.APPEND
     */
    public static void StringToFile(String content, String filePath, OpenOption... options) {

        // split filePath
        int endSeparator = filePath.lastIndexOf(File.separator);
        String dirPath = filePath.substring(0, endSeparator);
        String fileName = filePath.substring(endSeparator + 1);

        //  generate file
        StringToFile(content, dirPath, fileName, options);
    }

    /**
     * copy resources dir file to new file
     *
     * @param resourceFilePath resourceFilePath
     * @param filePath filePath
     * @param options options
     */
    public static void copyResourceFile(String resourceFilePath, String filePath, OpenOption... options) {

        DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();
        Resource resource = defaultResourceLoader.getResource(resourceFilePath);

        Path file = generateFile(filePath);
        try {
            Files.write(file, IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8).getBytes(), options);
        } catch (IOException e) {
            throw new OxygenException("copy resource file error");
        }
    }
}
