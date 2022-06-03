package br.com.blz.testjava.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.net.Socket;

@TestConfiguration
public class RedisEmbeddedConfig {

    private RedisServer redisServer;
    private String host;
    private int port;

    public RedisEmbeddedConfig(@Value("${spring.redis.host}") String host, @Value("${spring.redis.port}") int port) {
        redisServer = new RedisServer(port);
        this.host = host;
        this.port = port;
    }

    private static boolean available(String host, int port) {
        try (Socket ignored = new Socket(host, port)) {
            return false;
        } catch (IOException ignored) {
            return true;
        }
    }

    @PostConstruct
    public void init() {
        if (available(host, port)) {
            redisServer.start();
        }
    }

    @PreDestroy
    public void destroy() {
        redisServer.stop();
    }

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(host, port);
    }

}
