package com.barnesicle.user.config;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.reactive.RedisStringReactiveCommands;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

//@Configuration
public class RedisConfiguration {

    @Value("${redis.url}")
    private String url;

    @Value("${redis.port}")
    private int port;

    @Value("${redis.timeout.seconds}")
    private int timeout;

    @Bean
    public RedisStringReactiveCommands<String, String> redisStringReactiveCommands() {
        RedisURI redisURI = new RedisURI(url, port, Duration.ofSeconds(timeout));
        RedisClient client = RedisClient.create(redisURI);
        return client.connect().reactive();
    }


}