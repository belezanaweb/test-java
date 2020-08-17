package br.com.blz.testjava.database;

import br.com.blz.testjava.model.Product;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Component
public class ProductDatabase {

    private final Map<Long, Product> base = new TreeMap<>();

    public Product createProduct(Product product) {
        verifyExists(product.getSku());
        base.put(product.getSku(), product);
        return product;
    }

    public Product updateProduct(Product product) {
        verifyNotExists(product.getSku());
        base.put(product.getSku(), product);
        return product;
    }

    public Optional<Product> getProduct(Long sku) {
        return Optional.ofNullable(base.get(sku));
    }

    public void deleteProduct(Long sku) {
        base.remove(sku);
    }

    public Boolean exists(Long sku) {
        return base.containsKey(sku);
    }

    private void verifyExists(Long sku) {
        if (exists(sku)) {
            throw new IllegalArgumentException("The value is already in the base.");
        }
    }

    private void verifyNotExists(Long sku) {
        if (!exists(sku)) {
            throw new IllegalArgumentException("The value not exists in the base.");
        }
    }

    public void clearBase() {
        base.clear();
    }
}
