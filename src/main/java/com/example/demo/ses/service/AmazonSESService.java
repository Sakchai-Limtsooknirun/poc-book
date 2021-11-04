package com.example.demo.ses.service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

/**
 * @author sergeykargopolov
 */
@Service
public class AmazonSESService {

    private final AmazonSimpleEmailService client;

    public AmazonSESService(AmazonSimpleEmailService amazonSimpleEmailService) {
        this.client = amazonSimpleEmailService;
    }

    // This address must be verified with Amazon SES.
    final String FROM = "sakchai.limtsooknirun@scbtechx.io";
    final String TO = "sakchai.lsr@gmail.com";

    // The subject line for the email.
    final String SUBJECT = "One last step to complete your registration with PhotoApp";

    // The HTML body for the email.
    final String HTMLBODY = "<h1>Please verify your email address</h1>"
            + "<p>Thank you for registering with our mobile app. To complete registration process and be able to log in,"
            + " click on the following link: "
            + "<a href='http://localhost:8080/MOBILE-APP-WS-DEV/verify-email.jsp?token=$tokenValue'>"
            + "Final step to complete your registration"
            + "</a><br/><br/>"
            + "Thank you! And we are waiting for you inside!";
    // The email body for recipients with non-HTML email clients.
    final String TEXTBODY = "Please verify your email address. "
            + "Thank you for registering with our mobile app. To complete registration process and be able to log in,"
            + " open then the following URL in your browser window: "
            + " http://localhost:8080/MOBILE-APP-WS-DEV/verify-email.jsp?token=$tokenValue"
            + " Thank you! And we are waiting for you inside!";

    public Map<String, SendEmailResult> SendEmail() {
        try {
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(TO))
                    .withMessage(new Message().withBody(new Body()
                                    .withHtml(new Content().withCharset("UTF-8").withData(HTMLBODY))
                                    .withText(new Content().withCharset("UTF-8").withData(TEXTBODY)))
                            .withSubject(new Content().withCharset("UTF-8").withData(SUBJECT)))
                    .withSource(FROM);
            SendEmailResult result = client.sendEmail(request);
            System.out.println("Email sent!");
            return Map.of("messageId", result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Collections.emptyMap();
    }
}