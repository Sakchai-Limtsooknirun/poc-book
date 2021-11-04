package com.example.demo.keyspacenotification.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RedisDemoService {
    private final RedisTemplate<String, String> redisTemplate;

    public RedisDemoService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setOrderDataWithTTL(String orderId, String refId) {
        redisTemplate.opsForValue().set("orderIdKeyExpired:"+orderId+":"+refId, "fix", Duration.ofSeconds(5));
    }

    public void setOrderData(String orderId, String refId, String data) {
        redisTemplate.opsForValue().set("orderId:"+orderId+":"+refId, data);
    }

    public void delOrderData(String orderId, String refId) {
        redisTemplate.delete("orderId:"+orderId+":"+refId);
    }

    public String getOrderData(String orderId, String refId) {
        return redisTemplate.opsForValue().get("orderId:"+orderId+":"+refId);
    }
}
