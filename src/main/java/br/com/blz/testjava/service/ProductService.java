package br.com.blz.testjava.service;

import br.com.blz.testjava.controller.resources.ProductRequest;
import br.com.blz.testjava.controller.resources.ProductResponse;
import br.com.blz.testjava.persistence.entity.ProductInventory;
import br.com.blz.testjava.persistence.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductResponse getProductBySku(Long sku) {

        List<ProductInventory> productInventory = productRepository.findBySku(sku); // TODO implement NotFoundException

        productInventory.stream()
            .map(this::buildResponseObject)

        return new ProductResponse();
    }

    private ProductResponse buildResponseObject(ProductInventory productInventory) {
        ProductResponse productResponse = new ProductResponse(productInventory.getSku(), productInventory.)
    }

    public void createProduct(ProductRequest request) {
    }

    public ProductResponse updateProduct(ProductRequest request, Long sku) {
        return new ProductResponse();
    }

    public void deleteProduct(Long sku) {
    }
}
