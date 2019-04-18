package br.com.blz.testjava.service;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.wrapper.ProductRepositoryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductService {

    @Autowired
    protected ProductRepositoryWrapper repository;

    public Product findBySku(Long sku) {
        return repository.findOne(sku);
    }

    public Iterable<Product> findAll() {
        return repository.findAll();
    }

    public Product update(Product product) {
        return repository.save(product);
    }

    public Product create(Product product) {
        return repository.save(product);
    }

    public void remove(Long sku) {
        repository.delete(sku);
    }


}
