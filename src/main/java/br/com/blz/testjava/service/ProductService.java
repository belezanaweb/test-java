package br.com.blz.testjava.service;

import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.exception.ProductAlreadyExistException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.model.ProductEntity;
import br.com.blz.testjava.repository.ProductRepositoryImpl;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

    private final ProductRepositoryImpl repository;

    public ProductService(ProductRepositoryImpl repository) {
        this.repository = repository;
    }

    public void createProduct(final ProductDTO product) {
        ProductEntity productEntity = repository.findProductBySku(product.getSku());
        if (productEntity != null) {
            throw new ProductAlreadyExistException("Product " + product.getSku() + " already registered.");
        }
        repository.createProduct(product.buildProductEntity(product));
    }

    public void updateProduct(final ProductDTO product, Integer sku) {
        ProductEntity productEntity = repository.findProductBySku(sku);
        if (productEntity == null) {
            throw new ProductNotFoundException("Product " + sku + " not found.");
        }
        repository.updateProduct(product.buildProductEntity(product));
    }

    public ProductDTO recoveryProduct(Integer sku) {
        ProductEntity productEntity = repository.findProductBySku(sku);
        if (productEntity == null) {
            throw new ProductNotFoundException("Product " + sku + " not found.");
        }
        return productEntity.buildProductDTO(productEntity);
    }

    public void deleteProduct(final Integer sku) {
        ProductEntity productEntity = repository.findProductBySku(sku);
        if (productEntity == null) {
            throw new ProductNotFoundException("Product " + sku + " not found.");
        }
        repository.deleteProduct(sku);
    }
}
