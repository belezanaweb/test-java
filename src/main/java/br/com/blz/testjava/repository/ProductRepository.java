package br.com.blz.testjava.repository;

import br.com.blz.testjava.domain.api.request.CreateProductRequest;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class ProductRepository {

    private Map<Long, CreateProductRequest> products = new HashMap<>();

    public Optional<CreateProductRequest> findBySku(Long sku) {
        return Optional.ofNullable(products.get(sku));
    }

    public CreateProductRequest insert(CreateProductRequest product) {
        products.put(product.getSku(), product);
        return product;
    }

    public Optional<CreateProductRequest> delete(Long sku) {
        return Optional.ofNullable(products.remove(sku));
    }
}
