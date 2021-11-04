package com.example.demo.mail.controller;

import com.example.demo.mail.service.EmailServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping(path = "/email/test", produces = "application/json")
public class EmailServiceController {
    private final EmailServiceImpl emailService;

    public EmailServiceController(EmailServiceImpl emailService) {
        this.emailService = emailService;
    }

    @PostMapping()
    public ResponseEntity<?> sendEmailTest() throws MessagingException {
        emailService.sendMimeTypeMessage("sakchai.lsr@gmail.com",
                "test",
                "<html><body><img src='cid:identifier1234' width=200 height=200></body></html>");
        return ResponseEntity.ok().build();
    }


}
