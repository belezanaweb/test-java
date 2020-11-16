package br.com.blz.testjava.service;

import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.exception.ProductAlreadyExistException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product findProduct(Integer sku){
        return productRepository.findById(sku)
            .orElseThrow(() -> new ProductNotFoundException("Product", "sku", sku));
    }

    public Product createProduct(Product product){
        if(Objects.nonNull(findProduct(product.getSku())))
            throw new ProductAlreadyExistException("Product", "sku", product.getSku());
        else
            return productRepository.save(product);
    }

    public Product modifyProduct(Integer sku, Product product){
        if(Objects.nonNull(findProduct(sku)))
            return productRepository.save(product);
        else
            throw new ProductNotFoundException("Product", "sku", sku);
    }

    public ResponseEntity<?> deleteProduct(Integer sku){
        Product productFinded = findProduct(sku);
        if(Objects.nonNull(productFinded)){
            productRepository.delete(productFinded);
            return ResponseEntity.ok().build();
        }
        else {
            throw new ProductNotFoundException("Product", "sku", sku);
        }
    }
}
