package br.com.blz.testjava.service.impl;

import br.com.blz.testjava.controller.request.ProductRequest;
import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public Set<Product> findAll() {
        return null;
    }

    @Override
    public void create(ProductRequest productRequest) {

    }
}
