package br.com.blz.testjava.infrastructure.config;

import br.com.blz.testjava.product.Product;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DbConfig {
    private List<Product> productDb = new ArrayList<Product>();

    public Product saveProductDb(Product product) {
        productDb.add(product);
        return product;
    }

    public Optional<Product> findBySkuProductDb(Long sku) {
        return productDb.stream()
                        .filter(product -> Objects.equals(product.getSku(), sku))
                        .findAny();
    }

    public List<Product> findAllProductsDb() {
        return productDb;
    }

    public void deleteProductDb(Long sku) {
        Product product = findBySkuProductDb(sku).get();
        productDb.remove(product);
    }
}
