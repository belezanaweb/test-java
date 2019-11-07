package br.com.blz.testjava.repository;

import br.com.blz.testjava.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class ProductRepository {

    private Map<Long, Product> products = new HashMap<>();

    public void save(Product product) {
        products.put(product.getSku(), product);
    }

    public Optional<Product> findById(Long sku) {
        return Optional.ofNullable(products.get(sku));
    }

    public void deleteById(Long sku) {
        products.remove(sku);
    }

    public void update(Long sku, Product product) {
        products.replace(sku, product);
    }
}
