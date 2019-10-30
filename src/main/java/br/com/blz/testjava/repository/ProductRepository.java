package br.com.blz.testjava.repository;

import br.com.blz.testjava.model.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class ProductRepository {
    private static Map<Integer, Product> PRODUCTS = new HashMap<Integer, Product>();

    public void save(Product product) {
        PRODUCTS.put(product.getSku(), product);
    }

    public Optional<Product> getBySku(Integer sku) {
        return Optional.ofNullable(PRODUCTS.get(sku));
    }

    public void delete(Integer sku) {
        PRODUCTS.remove(sku);
    }
}
