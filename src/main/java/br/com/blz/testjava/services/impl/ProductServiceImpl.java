package br.com.blz.testjava.services.impl;

import br.com.blz.testjava.exceptions.BusinessException;
import br.com.blz.testjava.model.entities.Product;
import br.com.blz.testjava.model.repository.ProductRepository;
import br.com.blz.testjava.services.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        if (productRepository.existsBySku(product.getSku())) {
            throw new BusinessException("SKU already used by other product.");
        }
        return productRepository.save(product);
    }
}
