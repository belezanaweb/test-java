package br.com.blz.testjava.infra.ports;

import br.com.blz.testjava.core.domain.Product;
import br.com.blz.testjava.core.ports.SaveProductCachePort;
import br.com.blz.testjava.infra.data.ProductData;
import lombok.NonNull;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class SaveProductCachePortImpl implements SaveProductCachePort {

    private final RedisTemplate<String, Object> redisTemplate;

    public SaveProductCachePortImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void saveProduct(@NonNull Product product) {
        redisTemplate.opsForValue().set(product.getSku().toString(), ProductData.from(product));
    }

}
