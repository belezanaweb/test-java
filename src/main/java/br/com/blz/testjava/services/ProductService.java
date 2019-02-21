package br.com.blz.testjava.services;

import br.com.blz.testjava.entities.Product;
import br.com.blz.testjava.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    public Product insert(final Product product) {
        return this.productRepository.save(product);
    }

    public Optional<Product> findBySku(final Long sku) {
        return this.productRepository.findBySku(sku);
    }

    public void delete(Long sku) {
        Optional<Product> product = this.findBySku(sku);
        if(!product.isPresent())
            throw new IllegalArgumentException("Sku inválido!");
        this.productRepository.delete(product.get().getId());
    }

}
