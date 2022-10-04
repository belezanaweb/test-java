package br.com.blz.testjava.infrastracture.product.persistence;

import br.com.blz.testjava.domain.product.Sku;
import br.com.blz.testjava.infrastracture.exceptions.ConstraintViolationExceptionException;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class ProductRepository {

    private final Map<Long, ProductEntity> products;

    public ProductRepository() {
        this.products = new LinkedHashMap<>();
    }

    public ProductEntity save(ProductEntity productEntity) {
        if (products.containsKey(productEntity.getSku())) {
            throw ConstraintViolationExceptionException.with("Produto", Sku.from(productEntity.getSku()), "sku");
        }
        products.put(productEntity.getSku(), productEntity);
        return productEntity;
    }

    public ProductEntity update(ProductEntity productEntity) {
        products.put(productEntity.getSku(), productEntity);
        return productEntity;
    }

    public Optional<ProductEntity> findBySku(Long sku) {
        return Optional.ofNullable(products.get(sku));
    }

    public void delete(Long sku) {
        products.remove(sku);
    }

    public Integer count() {
        return products.size();
    }
}
