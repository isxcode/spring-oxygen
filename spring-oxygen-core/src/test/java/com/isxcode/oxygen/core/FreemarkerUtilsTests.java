package com.isxcode.oxygen.core;

import com.isxcode.oxygen.core.config.OxygenCoreAutoConfiguration;
import com.isxcode.oxygen.core.exception.OxygenException;
import com.isxcode.oxygen.core.file.FileUtils;
import com.isxcode.oxygen.core.freemarker.FreemarkerUtils;
import com.isxcode.oxygen.core.pojo.Dog;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(
		classes = {
			OxygenCoreAutoConfiguration.class,
			FreeMarkerAutoConfiguration.class,
			MailSenderAutoConfiguration.class
		})
public class FreemarkerUtilsTests {

	@Value("${test.templateName}")
	private String templateName;

	private final Dog dog = new Dog("wang", 15);

	private final String templateContent = "this dog name is ${name} and age is ${age}";

	@Test
	public void testContentToFile() {

		if (Files.exists(Paths.get("freemarker1.txt"))) {
			FileUtils.recursionDeleteFile("freemarker1.txt");
		}

		try {
			FreemarkerUtils.contentToFile(templateContent, dog, "freemarker1.txt");
		} catch (OxygenException e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	@Test
	public void testContentToString() {

		try {
			System.out.println(FreemarkerUtils.contentToString(templateContent, dog));
		} catch (OxygenException e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	@Test
	public void testTemplateToFile() {

		if (Files.exists(Paths.get("freemarker2.txt"))) {
			FileUtils.recursionDeleteFile("freemarker2.txt");
		}

		try {
			FreemarkerUtils.templateToFile(templateName, dog, "freemarker2.txt");
		} catch (OxygenException e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	@Test
	public void testTemplateToString() {

		try {
			System.out.println(FreemarkerUtils.templateToString(templateName, dog));
		} catch (OxygenException e) {
			System.out.println(e.getMessage());
			throw e;
		}

		FileUtils.recursionDeleteFile("freemarker1.txt");
		FileUtils.recursionDeleteFile("freemarker2.txt");
	}
}
