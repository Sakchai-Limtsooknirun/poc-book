package com.example.demo.keyspacenotification.configuration;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.util.Arrays;

@Configuration
class JsonRedisSerializer implements RedisSerializer<Object> {

    private final Gson gson;

    public JsonRedisSerializer() {
        gson = new Gson();
        gson.newBuilder().setPrettyPrinting();
    }


    @Override
    public byte[] serialize(Object t) {
        return gson.toJson(t).getBytes();

    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {

        if (bytes == null) {
            return null;
        }

        try {
            return gson.fromJson(Arrays.toString(bytes), Object.class);
        } catch (Exception e) {
            throw new SerializationException(e.getMessage(), e);
        }
    }
}
