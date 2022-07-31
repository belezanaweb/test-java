package br.com.blz.testjava.service;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepositoryImpl repository;

    public ProductService( ProductRepositoryImpl repository){
        this.repository = repository;
    }

    public void saveProduct(final Product product) {
        repository.save(product);
    }

    public void deleteProduct(final Integer sku) {
        repository.delete(sku);
    }

    public void findBySku(final Integer sku) {
        repository.findBySku(sku);
    }
}
