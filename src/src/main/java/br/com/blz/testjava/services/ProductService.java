package br.com.blz.testjava.services;

import br.com.blz.testjava.exception.ProductAlreadyExistsException;
import br.com.blz.testjava.exception.RecordNotFoundException;
import br.com.blz.testjava.models.Product;
import br.com.blz.testjava.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class ProductService {

    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        if(productRepository.existsBySku(product.getSku())){
            throw new ProductAlreadyExistsException("Product Already Exists");
        }

        return productRepository.save(product);
    }

    public Product findBySku(Long sku){
        if(!productRepository.existsBySku(sku)){
            throw new RecordNotFoundException("Product Not Found");
        }
        return productRepository.findBySku(sku);
    }

    public void deleteProduct(Long sku){
        if(!productRepository.existsBySku(sku)){
            throw new RecordNotFoundException("Product Not Found");
        }

        Product product = productRepository.findBySku(sku);
        productRepository.delete(product);
    }
}
