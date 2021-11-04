package com.example.demo.keyspacenotification.controller;

import com.example.demo.keyspacenotification.service.RedisDemoService;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/test")
public class Controller {

    private final RedisDemoService redisDemoService;
    private final Gson gson;

    public Controller(RedisDemoService redisDemoService, Gson gson) {
        this.redisDemoService = redisDemoService;
        this.gson = gson;
    }

    @PostMapping("/session")
    public ResponseEntity<?> createData() {
        String keyRefId = UUID.randomUUID().toString();
        String orderUniqId = UUID.randomUUID().toString();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("orderId", "123");
        redisDemoService.setOrderData(orderUniqId, keyRefId, gson.toJson(hashMap));
        redisDemoService.setOrderDataWithTTL(orderUniqId, keyRefId);
        return ResponseEntity.ok().build();
    }


}
