package br.com.blz.testjava.service;

import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product) {
        Product productSave = productRepository.save(product);
        return productSave;
    }

    public List<Product> list() {
        return productRepository.findAll();
    }

    public Optional<Product> findBySku(Long sku) {
        return productRepository.findById(sku);
    }

    public void delete(Product product) {
        productRepository.delete(product);
    }

}
