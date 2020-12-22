package br.com.blz.testjava.service;

import br.com.blz.testjava.controller.resources.ProductRequest;
import br.com.blz.testjava.controller.resources.ProductResponse;
import br.com.blz.testjava.exception.NotFoundException;
import br.com.blz.testjava.exception.SkuAlreadyRegisteredException;
import br.com.blz.testjava.persistence.entity.Product;
import br.com.blz.testjava.persistence.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.fasterxml.uuid.Generators.timeBasedGenerator;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductResponse getProductBySku(Long sku) {
        return new ProductResponse(productRepository.findBySku(sku).orElseThrow(() -> new NotFoundException("Product not found.")));
    }

    public void createProduct(ProductRequest request) {
        if (isSkuRegistered(request.getSku())) {
            throw new SkuAlreadyRegisteredException(String.format("Product with sku %s already exists!", request.getSku()));
        }

        productRepository.save(new Product(timeBasedGenerator().generate(), request));
    }

    private boolean isSkuRegistered(Long sku) {
        return productRepository.findBySku(sku).isPresent();
    }

    public ProductResponse updateProduct(ProductRequest request) {

        Optional<Product> productOpt = productRepository.findBySku(request.getSku());

        if (!productOpt.isPresent()) {
            throw new NotFoundException("Product not found!");
        }

        return new ProductResponse(
            productRepository.save(
                new Product(productOpt.get().getId(), request)));
    }

    public void deleteProduct(Long sku) {
        Optional<Product> productOpt = productRepository.findBySku(sku);

        if (!productOpt.isPresent()) {
            throw new NotFoundException("Product not found!");
        }

        productRepository.delete(productOpt.get());
    }
}
