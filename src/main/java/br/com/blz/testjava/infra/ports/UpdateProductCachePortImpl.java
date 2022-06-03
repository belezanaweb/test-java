package br.com.blz.testjava.infra.ports;

import br.com.blz.testjava.core.domain.Product;
import br.com.blz.testjava.core.ports.UpdateProductCachePort;
import br.com.blz.testjava.infra.data.ProductData;
import lombok.NonNull;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class UpdateProductCachePortImpl implements UpdateProductCachePort {

    private final RedisTemplate<String, Object> redisTemplate;

    public UpdateProductCachePortImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void updateProduct(@NonNull Product product, @NonNull String sku) {
        redisTemplate.opsForValue().getAndSet(sku, ProductData.from(product, sku));
    }
}
