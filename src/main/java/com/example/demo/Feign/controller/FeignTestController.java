package com.example.demo.Feign.controller;

import com.example.demo.Feign.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/feign/test", produces = "application/json")
public class FeignTestController {

    private final CustomerService customerService;

    @Autowired
    public FeignTestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("{rmId}")
    public ResponseEntity<?> getCustomerData(@PathVariable String rmId) {
        return ResponseEntity.ok().body(customerService.getCustomer(rmId));
    }
}
