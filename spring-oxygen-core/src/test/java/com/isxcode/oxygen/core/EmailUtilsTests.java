package com.isxcode.oxygen.core;

import com.isxcode.oxygen.core.config.OxygenCoreAutoConfiguration;
import com.isxcode.oxygen.core.email.EmailUtils;
import com.isxcode.oxygen.core.exception.OxygenException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles
@ContextConfiguration(classes = {OxygenCoreAutoConfiguration.class, JavaMailSenderImpl.class, FreeMarkerConfigurer.class})
public class EmailUtilsTests {

    @Test
    public void testSendEmail() {

        String email = "ispong@outlook.com";
        String emailContent = "hello,test for oxygen";
        String senderName = "ispong";

        try {
            EmailUtils.sendSimpleEmail(email, emailContent, "test1", senderName);
        } catch (OxygenException e) {
            System.out.println(e.getMessage());
        }

        try {
            List<String> emails = new ArrayList<>();
            emails.add(email);
            EmailUtils.sendSimpleEmail(emails, emailContent, "test2", senderName);
        } catch (OxygenException e) {
            System.out.println(e.getMessage());
        }

        try {
            EmailUtils.sendNormalHtmlEmail(email, emailContent, "test3", senderName);
        } catch (OxygenException e) {
            System.out.println(e.getMessage());
        }

    }


}

