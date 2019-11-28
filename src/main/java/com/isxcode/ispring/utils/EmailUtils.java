package com.isxcode.ispring.utils;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 邮箱工具
 *
 * @author ispong
 * @date 2019-11-28
 * @version v0.1.0
 */
@Component
public class EmailUtils {

    private static JavaMailSenderImpl mailSender;

    @Resource
    public void setMailSenderSon(MailSender mailSender) {
        EmailUtils.mailSender = (JavaMailSenderImpl) mailSender;
    }

    /**
     * 发送html的邮件
     *
     * @param fromEmail 起始邮箱
     * @param toEmail 目标邮箱
     * @param context 邮件内容
     * @param subject 邮件主题
     * @since 2019-11-28
     */
    public static void sendHtmlEmail(String fromEmail, String toEmail, String context,String subject){

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setSubject(subject);
            helper.setFrom(fromEmail);
            helper.setTo(toEmail);

            helper.setText(context, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
