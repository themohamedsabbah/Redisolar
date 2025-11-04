package com.codingwithsabbah.redisolar.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisConfiguration {
    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private Integer port;

    @Bean
    public JedisPool jedisPool() {
        return new JedisPool(
                new JedisPoolConfig(),
                host,
                port);
    }
}
