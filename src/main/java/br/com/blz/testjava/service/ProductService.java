package br.com.blz.testjava.service;

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    public Product findBySku(Long sku) {
        return this.productRepository.findBySku(sku);
    }

}
