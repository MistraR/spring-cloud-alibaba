package com.sample.mall.goods.config;

import io.rebloom.client.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 描述
 *
 * @author mistra@future.com
 * @date 2022/10/14
 */
@Configuration
public class RedisConfig {

    @Value("${Spring.redis.host}")
    private String host;
    @Value("${Spring.redis.port}")
    private int port;
    @Value("${Spring.redis.bloom-filter.name}")
    private String name;
    @Value("${Spring.redis.bloom-filter.init-capacity}")
    private long initCapacity;
    @Value("${Spring.redis.bloom-filter.error-rate}")
    private double errorRate;

    @Bean
    public <T> RedisTemplate<String, T> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, T> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    @Bean
    public Client redisBloomClient() {
        Client redisBloomClient = new Client(host, port);
        redisBloomClient.createFilter(name, initCapacity, errorRate);
        return redisBloomClient;
    }
}
