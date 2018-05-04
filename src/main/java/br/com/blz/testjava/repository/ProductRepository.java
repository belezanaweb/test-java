package br.com.blz.testjava.repository;

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.exception.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class ProductRepository {

    private static Set<Product> PRODUCTS = new HashSet<>();

    public void cleanUp() {
        PRODUCTS = new HashSet<>();
    }

    public void save(Product product) {

        if (PRODUCTS.contains(product)) {
            throw new DataIntegrityViolationException("Product with sku=" + product.getSku() + " already exists");
        }

        PRODUCTS.add(product);
    }

    public Optional<Product> findBySku(int sku) {
        return PRODUCTS.stream().filter(product -> product.getSku() == sku).findFirst();
    }

    public void update(Product product) {

        if (!PRODUCTS.contains(product)) {
            throw new DataIntegrityViolationException("Product with sku=" + product.getSku() + " doesn't exists");
        }

        PRODUCTS.add(product);
    }

    public boolean remove(Integer sku) {
        return PRODUCTS.removeIf(p -> p.getSku() == sku);
    }
}
