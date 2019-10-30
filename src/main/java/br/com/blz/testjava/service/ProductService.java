package br.com.blz.testjava.service;

import br.com.blz.testjava.exception.ProductBusinessException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product getOne(Integer sku) {
        return this.repository.getBySku(sku).orElseThrow(() -> new ProductNotFoundException(sku));
    }

    public void save(Product product) {
        this.repository.getBySku(product.getSku()).ifPresent(s -> {
            throw new ProductBusinessException("Produto já cadastrado");
        });
        this.repository.save(product);
    }

    public void update(Product product) {
        this.getOne(product.getSku()); //get already check if exists
        this.repository.save(product);
    }

    public void delete(Integer sku) {
        this.getOne(sku); //get already check if exists
        this.repository.delete(sku);
    }
}
