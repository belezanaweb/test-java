package br.com.blz.testjava.service;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.Repository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProductService {

    private final Repository repository;

    public ProductService(Repository repository) {
        this.repository = repository;
    }

    public Product save(Product product) {
        return this.repository.save(product);
    }

    public Optional<Product> findBySku(Long sku) {
        return this.repository.findBySku(sku);
    }

    public void deleteBySku(Long sku) {
        this.repository.deleteBySku(sku);
    }

    public Product update(Product productToUpdate) {
        return this.repository.update(productToUpdate);
    }

    public int count() {
        return this.repository.count();
    }
}
