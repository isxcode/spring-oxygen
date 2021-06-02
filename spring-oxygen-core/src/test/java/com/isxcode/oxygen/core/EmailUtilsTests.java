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

@SpringBootTest
@ActiveProfiles
@ContextConfiguration(classes = {OxygenCoreAutoConfiguration.class, JavaMailSenderImpl.class, FreeMarkerConfigurer.class})
public class EmailUtilsTests {

//    @Test
//    public void testSendEmail() {
//        try {
//            EmailUtils.sendSimpleEmail("ispong", "hello test", "test", "ispong");
//        } catch (OxygenException e) {
//            System.out.println(e.getMessage());
//        }
//    }

}

