package com.isxcode.oxygen.core;

import com.isxcode.oxygen.core.file.FileUtils;
import java.io.File;
import java.nio.file.StandardOpenOption;
import org.junit.jupiter.api.Test;

public class FileUtilsTests {

	@Test
	public void testGenerateDir() {

		FileUtils.generateDirs("test");
	}

	@Test
	public void testGenerateFile() {

		FileUtils.generateFile("test" + File.separator + "file" + File.separator + "file1.test");
	}

	@Test
	public void testStringToFile() {

		FileUtils.StringToFile(
				"hello",
				"test" + File.separator + "file" + File.separator + "file2.test",
				StandardOpenOption.WRITE);
	}

	@Test
	public void testCopyResourceFile() {

		FileUtils.copyResourceFile(
				"application-test.yml",
				"test" + File.separator + "file" + File.separator + "file3.test",
				StandardOpenOption.WRITE);
	}

	@Test
	public void testRecursionDeleteFile() {

		FileUtils.recursionDeleteFile("test");
	}
}
