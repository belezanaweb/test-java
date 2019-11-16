package br.com.blz.testjava.repository.inmemory.impl;

import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;

import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.repository.inmemory.ProductRepository;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class ProductRepositoryImpl implements ProductRepository<Product> {
    
    private static final Map<Long, Product> PRODUCT_MAP = new ConcurrentHashMap<>();
    
    @Override
    public Product save(Product p) {
        PRODUCT_MAP.put(p.getSku(), p);
        return p;
    }
    
    @Override
    public Optional findBySku(long sku) {
        return ofNullable(PRODUCT_MAP.get(sku));
    }
    
    @Override
    public boolean remove(long sku) {
        return nonNull(PRODUCT_MAP.remove(sku));
    }
    
    @Override
    public void clean() {
        PRODUCT_MAP.clear();
    }
}
