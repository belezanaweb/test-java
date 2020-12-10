package br.com.blz.testjava.service;

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.exception.EntityAlreadyExistsException;
import br.com.blz.testjava.exception.EntityNotFoundException;
import br.com.blz.testjava.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product save(Product p){
        final Product exists = productRepository.findBySku(p.getSku());
        if (Objects.nonNull(exists)){
            throw new EntityAlreadyExistsException(String.format("Product %d already saved",p.getSku()));
        }
        return productRepository.save(p);
    }

    public Product findBySku(Long sku){
        final Product product = productRepository.findBySku(sku);
        if(Objects.isNull(product)){
            throw new EntityNotFoundException(String.format("Product %d not found",sku));
        }
        return product;
    }

    public Product update(Product p){
        Product saved = productRepository.findBySku(p.getSku());
        saved.setName(p.getName());
        saved.setInventory(p.getInventory());
        return productRepository.save(saved);
    }

    public void delete(Long sku){
        final Product saved = productRepository.findBySku(sku);
        productRepository.delete(saved);
    }
}
