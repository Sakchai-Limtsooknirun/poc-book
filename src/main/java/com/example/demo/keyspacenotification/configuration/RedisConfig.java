package com.example.demo.keyspacenotification.configuration;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

@Configuration

class RedisConfig {


    @Bean
    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory);
        template.setDefaultSerializer(new JsonRedisSerializer());
        template.setValueSerializer(new JsonRedisSerializer());
        return template;
    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory(RedisProperties redisProperties) {
        RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration();
        standaloneConfig.setHostName(redisProperties.getHost());
        standaloneConfig.setPort(redisProperties.getPort());
        standaloneConfig.setDatabase(redisProperties.getDatabase());

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(standaloneConfig);
        jedisConnectionFactory.setUsePool(true);
        jedisConnectionFactory.setPoolConfig(buildPoolConfig(redisProperties));

        return jedisConnectionFactory;
    }

    private JedisPoolConfig buildPoolConfig(RedisProperties redisProperties) {
        RedisProperties.Pool poolConfig = redisProperties.getJedis().getPool();
        JedisPoolConfig pool = new JedisPoolConfig();
        pool.setMaxTotal(poolConfig.getMaxActive());
        pool.setMaxIdle(poolConfig.getMaxIdle());
        pool.setMinIdle(poolConfig.getMinIdle());
        pool.setTestOnBorrow(true);
        return pool;
    }

}