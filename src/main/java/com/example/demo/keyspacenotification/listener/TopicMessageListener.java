package com.example.demo.keyspacenotification.listener;

import com.example.demo.keyspacenotification.configuration.RedisListenerConfiguration;
import com.example.demo.keyspacenotification.service.RedisDemoService;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TopicMessageListener implements MessageListener {

    private final RedisDemoService redisDemoService;

    public TopicMessageListener(RedisDemoService redisDemoService, RedisListenerConfiguration redisListenerConfiguration) {
        this.redisDemoService = redisDemoService;
        redisListenerConfiguration.setTopicMessageListener(this);
    }


    @Override
    public void onMessage(Message message, byte[] bytes) {
        String keyExpired = new String(message.getBody());
//        String channel = new String(message.getChannel());
        System.out.println(keyExpired);
        List<String> keyExpiredId = Arrays.stream(keyExpired.split(":")).collect(Collectors.toList());
        String dataOrder = redisDemoService.getOrderData(keyExpiredId.get(1), keyExpiredId.get(2));
        System.out.println(dataOrder);
        if (dataOrder != null) {
            redisDemoService.delOrderData(keyExpiredId.get(1), keyExpiredId.get(2));
            System.out.println("my service processing");
        } else System.out.println("my service not process");
    }
}
