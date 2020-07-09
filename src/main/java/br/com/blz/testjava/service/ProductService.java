package br.com.blz.testjava.service;

import br.com.blz.testjava.exception.ProductAlreadyExists;
import br.com.blz.testjava.exception.ProductNotFound;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.repository.WarehouseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    public Product verifyAndCreateProduct(Product product){
        log.info("Receive new product sku: {}", product.getSku());

        if (this.findOneProductBySku(product.getSku()).isPresent()) {
            log.error("Product already exists sku: {}", product.getSku());
            throw new ProductAlreadyExists("Product already exists");
        }
        return productRepository.save(product);
    }

    public Product listProduct(String sku){
        log.info("Find product with sku: {}", sku);

        Optional<Product> productOptional = this.findOneProductBySku(sku);

        if (!productOptional.isPresent()) {
            log.info("Product not found by sku: ", sku);
            throw new ProductNotFound("Product not found");
        }

        return productOptional.get();
    }

    public Product updateProduct(String sku, Product product){
        log.info("Receive product to update sku: {}", product.getSku());

        product.setSku(sku);
        return productRepository.save(product);
    }

    public void deleteProduct(String sku){
        log.info("Receive product to delete sku: {}", sku);

        Product product = new Product();
        product.setSku(sku);
        productRepository.delete(product);
    }

    private Optional<Product> findOneProductBySku(String sku){
        return productRepository.findById(sku);
    }
}
