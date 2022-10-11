package com.isxcode.oxygen.core;

import com.isxcode.oxygen.core.file.FileUtils;
import java.io.File;
import java.nio.file.StandardOpenOption;
import org.junit.jupiter.api.Test;

public class FileUtilsTests {

	private final String dirPath = "file";

	@Test
	public void testGenerateDir() {

		FileUtils.generateDirs(dirPath);
	}

	@Test
	public void testGenerateFile() {

		FileUtils.generateFile(dirPath + File.separator + "file1.txt");
	}

	@Test
	public void testStringToFile() {

		FileUtils.StringToFile(
				"hello", dirPath + File.separator + "file2.txt", StandardOpenOption.WRITE);
	}

	@Test
	public void testCopyResourceFile() {

		FileUtils.copyResourceFile(
				"application-test.yml", dirPath + File.separator + "file3.txt", StandardOpenOption.WRITE);

		FileUtils.recursionDeleteFile(dirPath);
	}
}
