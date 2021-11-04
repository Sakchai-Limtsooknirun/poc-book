package com.example.demo.ses.controller;

import com.example.demo.ses.service.AmazonSESService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/ses/email/test", produces = "application/json")
public class AmazonSESController {

    private final AmazonSESService amazonSESService;

    public AmazonSESController(AmazonSESService amazonSESService) {
        this.amazonSESService = amazonSESService;
    }

    @PostMapping()
    public ResponseEntity<?> sendEmailTest() {
        return ResponseEntity.ok(amazonSESService.SendEmail());
    }
}
