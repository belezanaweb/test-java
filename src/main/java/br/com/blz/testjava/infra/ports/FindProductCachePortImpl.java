package br.com.blz.testjava.infra.ports;

import br.com.blz.testjava.core.domain.Product;
import br.com.blz.testjava.core.ports.FindProductCachePort;
import br.com.blz.testjava.infra.data.ProductData;
import lombok.NonNull;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class FindProductCachePortImpl implements FindProductCachePort {

    private final RedisTemplate<String, Object> redisTemplate;

    public FindProductCachePortImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Product findProduct(@NonNull String sku) {
        ProductData productData =  (ProductData) redisTemplate.opsForValue().get(sku);
        return productData != null ? productData.toProduct() : null;
    }

}
