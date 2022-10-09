package com.isxcode.oxygen.core;

import com.isxcode.oxygen.core.config.OxygenCoreAutoConfiguration;
import com.isxcode.oxygen.core.email.EmailUtils;
import com.isxcode.oxygen.core.exception.OxygenException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(
		classes = {
			MailSenderAutoConfiguration.class,
			OxygenCoreAutoConfiguration.class,
			FreeMarkerConfigurer.class
		})
public class EmailUtilsTests {

	@Value("${test.email}")
	private String email;

	@Value("${test.emailContent}")
	private String emailContent;

	@Value("${test.senderName}")
	private String senderName;

	@Test
	public void testSendEmail() {

		try {
			EmailUtils.sendSimpleEmail(email, emailContent, "test1", senderName);
		} catch (OxygenException e) {
			System.out.println(e.getMessage());
			throw e;
		}

		try {
			List<String> emails = new ArrayList<>();
			emails.add(email);
			EmailUtils.sendSimpleEmail(emails, emailContent, "test2", senderName);
		} catch (OxygenException e) {
			System.out.println(e.getMessage());
			throw e;
		}

		try {
			EmailUtils.sendNormalHtmlEmail(email, emailContent, "test3", senderName);
		} catch (OxygenException e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}
}
