package com.isxcode.oxygen.core.email;

import com.isxcode.oxygen.core.exception.OxygenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * email utils
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
public class EmailUtils {

    private static JavaMailSenderImpl javaMailSender;

    private static ThreadPoolTaskExecutor emailThread;

    public EmailUtils(MailSender mailSender, ThreadPoolTaskExecutor emailThread) {

        EmailUtils.emailThread = emailThread;
        EmailUtils.javaMailSender = (JavaMailSenderImpl) mailSender;
    }

    /**
     * send simple email
     *
     * @param email      email
     * @param content    content
     * @param subject    subject
     * @param senderName senderName
     * @since 0.0.1
     */
    public static void sendSimpleEmail(String email, String content, String subject, String senderName) {

        try {
            sendEmail(Collections.singletonList(email), content, subject, senderName, false, null, null);
        } catch (MessagingException e) {
            throw new OxygenException(e.getMessage());
        }
    }

    /**
     * send simple emails
     *
     * @param emails     emails
     * @param content    content
     * @param subject    subject
     * @param senderName senderName
     * @since 0.0.1
     */
    public static void sendSimpleEmail(List<String> emails, String content, String subject, String senderName) {

        try {
            sendEmail(emails, content, subject, senderName, false, null, null);
        } catch (Exception e) {
            throw new OxygenException(e.getMessage());
        }
    }

    /**
     * send html email
     *
     * @param email      email
     * @param content    content
     * @param subject    subject
     * @param senderName senderName
     * @since 0.0.1
     */
    public static void sendNormalHtmlEmail(String email, String content, String subject, String senderName) {

        try {
            sendEmail(Collections.singletonList(email), content, subject, senderName, true, null, null);
        } catch (Exception e) {
            throw new OxygenException(e.getMessage());
        }
    }

    /**
     * send email main
     *
     * @param toEmails      toEmails
     * @param emailContent  emailContent
     * @param subject       subject
     * @param senderName    senderName
     * @param isHtmlContent isHtmlContent
     * @param files         files
     * @param inlineFiles   inlineFiles
     * @throws MessagingException MessagingException
     * @since 0.0.1
     */
    public static void sendEmail(List<String> toEmails,
                                 String emailContent,
                                 String subject,
                                 String senderName,
                                 boolean isHtmlContent,
                                 List<File> files,
                                 Map<String, File> inlineFiles) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();

        if (javaMailSender.getUsername() == null) {
            throw new OxygenException("email username is null");
        }

        if (senderName == null || senderName.isEmpty()) {
            message.setFrom(Objects.requireNonNull(javaMailSender.getUsername()));
        }else{
            message.setFrom(senderName + "<" + Objects.requireNonNull(javaMailSender.getUsername()) + ">");
        }

        MimeMessageHelper helper = new MimeMessageHelper(message);

        if (files != null || inlineFiles != null) {
            helper = new MimeMessageHelper(message, true);
        }

        helper.setSubject(subject);
        helper.setText(emailContent, isHtmlContent);

        if (files != null) {
            for (File file : files) {
                helper.addAttachment(file.getName(), file);
            }
        }

        if (inlineFiles != null) {
            for (Map.Entry<String, File> entry : inlineFiles.entrySet()) {
                helper.addInline(entry.getKey(), entry.getValue());
            }
        }

        for (String toEmail : toEmails) {
            helper.setTo(toEmail);
            emailThread.execute(() -> {
                try {
                    javaMailSender.send(message);
                } catch (Exception e) {
                    log.error(e.getMessage());
                    log.info("email fail to send");
                }
            });
        }

    }
}
