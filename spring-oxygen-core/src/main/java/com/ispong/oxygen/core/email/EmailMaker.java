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

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Objects;

/**
 * spring-mail 发送邮件服务
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
@Component
public class EmailMaker {

    /**
     * inlineId 标识
     */
    private final static String INLINE_ID = "INLINE_ID";

    private static MailSender mailSender;

    private static SimpleMailMessage templateMessage;

    private static JavaMailSenderImpl javaMailSender;

    public EmailMaker(MailSender mailSender) {

        EmailMaker.mailSender = mailSender;
        EmailMaker.javaMailSender = (JavaMailSenderImpl) mailSender;
        EmailMaker.templateMessage = new SimpleMailMessage();
    }

    /**
     * 发送普通文本邮件
     *
     * @param toEmail     目标邮箱地址
     * @param textContent 邮件内容
     * @param subject     邮件主题
     * @since 2019-11-28
     */
    public static void sendTextEmail(String toEmail, String textContent, String subject) throws MessagingException {

        templateMessage.setTo(toEmail);
        templateMessage.setText(textContent);
        templateMessage.setSubject(subject);

        log.debug("sending email:" + templateMessage.toString());
        mailSender.send(templateMessage);
    }

    /**
     * 发送html类型的邮件
     *
     * @param toEmail     目标邮箱地址
     * @param htmlContent 邮件内容
     * @param subject     邮件主题
     * @since 2019-11-28
     */
    public static void sendHtmlEmail(String toEmail, String htmlContent, String subject) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);

        log.debug("sending email:" + message.toString());
        javaMailSender.send(message);
    }

    /**
     * 发送附件类型的邮件
     *
     * @param toEmail     发送邮箱地址
     * @param textContent 邮件内容
     * @param file        附件源
     * @param subject     邮箱主题
     * @since 2019-12-05
     */
    public static void sendAttachmentEmail(String toEmail, String textContent, Resource file, String subject) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(textContent);
        helper.addAttachment(Objects.requireNonNull(file.getFilename()), file);

        log.debug("sending email:" + message.toString());
        javaMailSender.send(message);
    }

    /**
     * 发送附件类型的邮件
     *
     * @param toEmail     发送邮箱地址
     * @param textContent 邮件内容
     * @param filePath    附件地址
     * @param subject     邮箱主题
     * @since 2019-12-05
     */
    public static void sendAttachmentEmail(String toEmail, String textContent, String filePath, String subject) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(textContent);
        FileSystemResource fileSystemResource = new FileSystemResource(new File(filePath));
        helper.addAttachment(Objects.requireNonNull(fileSystemResource.getFilename()), fileSystemResource);

        log.debug("sending email:" + message.toString());
        javaMailSender.send(message);
    }

    /**
     * 发送内嵌附件类型的邮件,设置较长超时时间
     *
     * @param toEmail 发送邮箱地址
     * @param file    附件资源
     * @param subject 邮箱主题
     * @since 2019-12-05
     */
    public static void sendInlineEmail(String toEmail, Resource file, String subject) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText("<html><body><img src='cid:" + INLINE_ID + "'></body></html>", true);
        helper.addInline(INLINE_ID, file);

        log.debug("sending email:" + message.toString());
        javaMailSender.send(message);
    }

    /**
     * 发送内嵌附件类型的邮件,设置较长超时时间
     *
     * @param toEmail  发送邮箱地址
     * @param filePath 附件地址
     * @param subject  邮箱主题
     * @since 2019-12-05
     */
    public static void sendInlineEmail(String toEmail, String filePath, String subject) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText("<html><body><img src='cid:" + INLINE_ID + "'></body></html>", true);
        FileSystemResource fileSystemResource = new FileSystemResource(new File(filePath));
        helper.addInline(INLINE_ID, fileSystemResource);

        log.debug("sending email:" + message.toString());
        javaMailSender.send(message);
    }
}
