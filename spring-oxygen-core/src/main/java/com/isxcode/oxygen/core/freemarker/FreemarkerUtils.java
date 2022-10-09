package com.isxcode.oxygen.core.freemarker;

import com.isxcode.oxygen.core.exception.OxygenException;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 * freemarker utils
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
public class FreemarkerUtils {

	private static final Configuration configuration =
			new Configuration(Configuration.VERSION_2_3_27);

	private static FreeMarkerConfigurer freeMarkerConfigurer;

	public FreemarkerUtils(FreeMarkerConfigurer freeMarkerConfigurer) {

		configuration.setTemplateLoader(new StringTemplateLoader());
		FreemarkerUtils.freeMarkerConfigurer = freeMarkerConfigurer;
	}

	/**
	 * generate template file to file
	 *
	 * @param templateFileName template file name
	 * @param filePath file generate path
	 * @param params freemarker params
	 * @throws OxygenException freemarker/io exception
	 * @since 0.0.1
	 */
	public static void templateToFile(String templateFileName, Object params, String filePath)
			throws OxygenException {

		Path fileIoPath = Paths.get(filePath);

		if (Files.isDirectory(fileIoPath)) {
			throw new OxygenException("file is directory");
		}

		if (Files.exists(fileIoPath)) {
			throw new OxygenException("file is exist");
		}

		try {
			Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateFileName);
			Path path = Files.createFile(fileIoPath);
			Files.write(
					path, FreeMarkerTemplateUtils.processTemplateIntoString(template, params).getBytes());
		} catch (TemplateException | IOException e) {
			throw new OxygenException(e.getMessage());
		}
	}

	/**
	 * generate template file to string
	 *
	 * @param templateFileName templateFileName
	 * @param params params
	 * @return string
	 * @throws OxygenException OxygenException
	 * @since 0.0.1
	 */
	public static String templateToString(String templateFileName, Object params) {

		try {
			Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateFileName);
			return FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
		} catch (TemplateException | IOException e) {
			throw new OxygenException(e.getMessage());
		}
	}

	/**
	 * generate content to file
	 *
	 * @param templateContent templateContent
	 * @param params params
	 * @param filePath filePath
	 * @since 0.0.1
	 */
	public static void contentToFile(String templateContent, Object params, String filePath) {

		Path fileIoPath = Paths.get(filePath);

		if (Files.isDirectory(fileIoPath)) {
			throw new OxygenException("file is directory");
		}

		if (Files.exists(fileIoPath)) {
			throw new OxygenException("file is exist");
		}

		try {
			Template template = new Template("", templateContent, configuration);
			Path path = Files.createFile(fileIoPath);
			Files.write(
					path, FreeMarkerTemplateUtils.processTemplateIntoString(template, params).getBytes());
		} catch (TemplateException | IOException e) {
			throw new OxygenException(e.getMessage());
		}
	}

	/**
	 * generate template content to string
	 *
	 * @param templateContent templateContent
	 * @param params params
	 * @return string
	 * @throws OxygenException OxygenException
	 * @since 0.0.1
	 */
	public static String contentToString(String templateContent, Object params) {

		try {
			Template template = new Template("", templateContent, configuration);
			return FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
		} catch (TemplateException | IOException e) {
			throw new OxygenException(e.getMessage());
		}
	}
}
