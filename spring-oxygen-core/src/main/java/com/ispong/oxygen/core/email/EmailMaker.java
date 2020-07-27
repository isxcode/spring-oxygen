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
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 发送电子邮件marker
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
public class EmailMaker {

    private static JavaMailSenderImpl javaMailSender;

    private static MailProperties mailProperties;

    private static ThreadPoolTaskExecutor emailThread;

    public EmailMaker(MailSender mailSender, MailProperties mailProperties, ThreadPoolTaskExecutor emailThread) {

        EmailMaker.emailThread = emailThread;
        EmailMaker.mailProperties = mailProperties;
        EmailMaker.javaMailSender = (JavaMailSenderImpl) mailSender;
    }

    /**
     * 发送电子邮件核心方法
     *
     * @param toEmails      目标邮箱地址
     * @param emailContent  邮件内容
     * @param subject       邮件主题
     * @param isHtmlContent 是否将邮件内容转成网页
     * @param files         附件列表
     * @param inlineFiles   嵌入式附件
     * @since 0.0.1
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

            // 是否支持附件操作
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
                    helper.addInline(entry.getKey(), entry.getValue());
                }
            }

            // 多线程发送邮件
            for (String toEmail : toEmails) {
                log.debug("sending to " + toEmail + " content:" + message.getContent());

                helper.setTo(toEmail);
                emailThread.execute(() -> javaMailSender.send(message));
            }
        } catch (MessagingException | IOException ex) {
            throw new OxygenException(ex.toString());
        }
    }

}
