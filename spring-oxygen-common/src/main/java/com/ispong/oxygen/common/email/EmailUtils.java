package com.ispong.oxygen.common.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Objects;

/**
 * spring-mail 发送邮件工具类
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019-11-28
 */
@Slf4j
@Component
public class EmailUtils {

    private static JavaMailSenderImpl mailSender;

    private static SimpleMailMessage templateMessage;

    @Resource
    public void setMailSender(JavaMailSender javaMailSender) {

        EmailUtils.mailSender = (JavaMailSenderImpl) javaMailSender;
    }

    @Resource
    public void setTemplateMessage(JavaMailSender javaMailSender) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(((JavaMailSenderImpl) javaMailSender).getUsername());
        EmailUtils.templateMessage = simpleMailMessage;
    }

    /**
     * 发送正常的邮件
     *
     * @param toEmail       目标邮箱地址
     * @param normalContext 邮件内容
     * @param subject       邮件主题
     * @since 2019-11-28
     */
    public static void sendNormalEmail(String toEmail, String normalContext, String subject) {

        templateMessage.setTo(toEmail);
        templateMessage.setText(normalContext);
        templateMessage.setSubject(subject);
        try {
            mailSender.send(templateMessage);
        } catch (MailException ex) {
            log.info("邮件发送异常,请做补救处理");
        }
    }

    /**
     * 发送html类型的邮件
     *
     * @param toEmail     目标邮箱地址
     * @param htmlContext 邮件内容
     * @param subject     邮件主题
     * @since 2019-11-28
     */
    public static void sendHtmlEmail(String toEmail, String htmlContext, String subject) {

        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(Objects.requireNonNull(mailSender.getUsername()));
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(htmlContext, true);
            mailSender.send(message);
        } catch (MessagingException ex) {
            log.info("邮件发送异常,请做补救处理");
        }
    }

    /**
     * 发送附件类型的邮件
     *
     * @param toEmail            发送邮箱地址
     * @param normalContext      邮件内容
     * @param attachmentResource 附件源
     * @param subject            邮箱主题
     * @since 2019-12-05
     */
    public static void sendAttachmentEmail(String toEmail, String normalContext, org.springframework.core.io.Resource attachmentResource, String subject) {

        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(Objects.requireNonNull(mailSender.getUsername()));
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(normalContext);
            helper.addAttachment(Objects.requireNonNull(attachmentResource.getFilename()), attachmentResource);
            mailSender.send(message);
        } catch (MessagingException ex) {
            log.info("邮件发送异常,请做补救处理");
        }
    }

    /**
     * 发送附件类型的邮件
     *
     * @param toEmail            发送邮箱地址
     * @param normalContext      邮件内容
     * @param attachmentFilePath 附件地址
     * @param subject            邮箱主题
     * @since 2019-12-05
     */
    public static void sendAttachmentEmail(String toEmail, String normalContext, String attachmentFilePath, String subject) {

        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(Objects.requireNonNull(mailSender.getUsername()));
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(normalContext);
            FileSystemResource fileSystemResource = new FileSystemResource(new File(attachmentFilePath));
            helper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
            mailSender.send(message);
        } catch (MessagingException ex) {
            log.info("邮件发送异常,请做补救处理");
        }
    }

    /**
     * 发送内嵌附件类型的邮件,设置较长超时时间
     *
     * @param toEmail            发送邮箱地址
     * @param attachmentFilePath 附件地址
     * @param subject            邮箱主题
     * @since 2019-12-05
     */
    public static void sendInlineEmail(String toEmail, String attachmentFilePath, String subject) {

        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(Objects.requireNonNull(mailSender.getUsername()));
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText("<html><body><img src='cid:inlineId'></body></html>", true);
            FileSystemResource fileSystemResource = new FileSystemResource(new File(attachmentFilePath));
            helper.addInline("inlineId", fileSystemResource);
            mailSender.send(message);
        } catch (MessagingException ex) {
            log.info("邮件发送异常,请做补救处理");
        }
    }

    /**
     * 发送内嵌附件类型的邮件,设置较长超时时间
     *
     * @param toEmail            发送邮箱地址
     * @param attachmentResource 附件资源
     * @param subject            邮箱主题
     * @since 2019-12-05
     */
    public static void sendInlineEmail(String toEmail, org.springframework.core.io.Resource attachmentResource, String subject) {

        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(Objects.requireNonNull(mailSender.getUsername()));
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText("<html><body><img src='cid:inlineId'></body></html>", true);
            helper.addInline("inlineId", attachmentResource);
            mailSender.send(message);
        } catch (MessagingException ex) {
            log.info("邮件发送异常,请做补救处理");
        }
    }
}
