package br.com.blz.testjava.service.impl;

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public Product create(Product product) {
        return product;
    }
}
