package br.com.blz.testjava.service;

import br.com.blz.testjava.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

}
