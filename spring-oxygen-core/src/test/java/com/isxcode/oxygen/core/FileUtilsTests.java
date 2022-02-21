package com.isxcode.oxygen.core;

import com.isxcode.oxygen.core.file.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class FileUtilsTests {

    private String userHomePath = System.getProperty("user.home");

    @Test
    public void testGenerateDir() {

        FileUtils.generateDirs(userHomePath + File.separator + "test");
    }

    @Test
    public void testGenerateFile() {

        FileUtils.generateFile(userHomePath + File.separator + "test" + File.separator + "file" + File.separator + "file.test");
    }

    @Test
    public void testStringToFile() {

        FileUtils.StringToFile("hello", userHomePath + File.separator + "test" + File.separator + "file" + File.separator + "file.test", StandardOpenOption.WRITE);
    }

    @Test
    public void testCopyResourceFile() {

        FileUtils.copyResourceFile("/test.txt", userHomePath + File.separator + "test" + File.separator + "file" + File.separator + "file.test", StandardOpenOption.WRITE);
    }

}

