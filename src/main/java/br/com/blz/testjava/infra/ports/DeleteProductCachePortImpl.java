package br.com.blz.testjava.infra.ports;

import br.com.blz.testjava.core.ports.DeleteProductCachePort;
import lombok.NonNull;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class DeleteProductCachePortImpl implements DeleteProductCachePort {

    private final RedisTemplate<String, Object> redisTemplate;

    public DeleteProductCachePortImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void deleteProduct(@NonNull String sku) {
        redisTemplate.opsForValue().getOperations().delete(sku);
    }

}
