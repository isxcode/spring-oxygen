/*
 * Copyright [2020] [ispong]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ispong.oxygen.core.email;

import com.ispong.oxygen.core.exception.OxygenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * spring-mail 发送邮件服务
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
public class EmailMaker {

    private static JavaMailSenderImpl javaMailSender;

    private static MailProperties mailProperties;

    public EmailMaker(MailSender mailSender, MailProperties mailProperties) {

        EmailMaker.mailProperties = mailProperties;
        EmailMaker.javaMailSender = (JavaMailSenderImpl) mailSender;
    }

    /**
     * 发送html类型的邮件
     *
     * @param toEmails     目标邮箱地址
     * @param emailContent 邮件内容
     * @param subject      邮件主题
     * @since 2019-11-28
     */
    public static void sendEmailMain(List<String> toEmails,
                                     String emailContent,
                                     String subject,
                                     boolean isHtmlContent,
                                     List<File> files,
                                     Map<String, File> inlineFiles) throws OxygenException {

        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message);

            // 可以上传附件
            if (files != null || inlineFiles != null) {
                helper = new MimeMessageHelper(message, true);
            }

            helper.setSubject(subject);
            helper.setText(emailContent, isHtmlContent);
            helper.setFrom(mailProperties.getUsername());

            // 遍历加入附件
            if (files != null) {
                for (File file : files) {
                    helper.addAttachment(file.getName(), file);
                }
            }

            // 遍历加入嵌入图片
            if (inlineFiles != null) {
                for (Map.Entry<String, File> entry : inlineFiles.entrySet()) {
                    String inlineName = entry.getKey();
                    File inlineFile = entry.getValue();
                    helper.addInline(inlineName, inlineFile);
                }
            }

            // 遍历发送
            for (String toEmail : toEmails) {
                helper.setTo(toEmail);
                log.debug("sending to " + toEmail + "  content:" + message.getContent());
                javaMailSender.send(message);
            }
        } catch (MessagingException | IOException ex) {
            throw new OxygenException(ex.toString());
        }
    }
}
