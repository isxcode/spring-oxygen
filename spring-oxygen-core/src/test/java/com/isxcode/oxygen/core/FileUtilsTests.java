package com.isxcode.oxygen.core;

import com.isxcode.oxygen.core.file.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.StandardOpenOption;

public class FileUtilsTests {

    @Test
    public void testGenerateDir() {

        FileUtils.generateDirs("test");
    }

    @Test
    public void testGenerateFile() {

        FileUtils.generateFile("test" + File.separator + "file" + File.separator + "file.test");
    }

    @Test
    public void testStringToFile() {

        FileUtils.StringToFile("hello", "test" + File.separator + "file" + File.separator + "file.test", StandardOpenOption.WRITE);
    }

    @Test
    public void testCopyResourceFile() {

        FileUtils.copyResourceFile("application-test.yml", "test" + File.separator + "file" + File.separator + "file.test", StandardOpenOption.WRITE);
    }

    @Test
    public void testRecursionDeleteFile() {

        FileUtils.recursionDeleteFile("test");
    }

}

