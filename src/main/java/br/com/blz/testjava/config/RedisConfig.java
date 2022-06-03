package br.com.blz.testjava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(lettuceConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        return template;
    }

    @Bean
    public RedisCacheManager cacheManager(LettuceConnectionFactory lettuceConnectionFactory) {
        Map<String, RedisCacheConfiguration> cacheNamesConfigurationMap = new HashMap<>();

        return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(lettuceConnectionFactory)
                .withInitialCacheConfigurations(cacheNamesConfigurationMap).build();
    }

}
