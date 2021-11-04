package com.example.demo.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailServiceImpl {

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleTextMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@scbtechx.io");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void sendMimeTypeMessage(String to, String subject, String content) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();

        // use the true flag to indicate you need a multipart message
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setFrom("noreplythis@scbtechx.io");
        helper.setSubject(subject);

        // use the true flag to indicate the text included is HTML
        helper.setText(content, true);

        // let's include the infamous windows Sample file (this time copied to c:/)
        FileSystemResource res = new FileSystemResource(new File("src/main/resources/germany.jpg"));
        helper.addInline("identifier1234", res);
        //addAttachment
        helper.addAttachment("germany.jpg", res);
        emailSender.send(message);
    }
}
