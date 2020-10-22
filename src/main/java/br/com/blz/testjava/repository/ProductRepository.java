package br.com.blz.testjava.repository;

import br.com.blz.testjava.model.Product;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;

@Component
public class ProductRepository {
    private HashMap<Long,Product> products = new HashMap();

    public Product save(Product product) {
        products.put(product.getSku(), product);
        return product;
    }

    public Optional<Product> findBySku(Long sku) {
        return Optional.ofNullable(products.get(sku));
    }

    public Product delete(Long sku) {
        return products.remove(sku);
    }
}
