package br.com.blz.testjava.repository.impl;

import br.com.blz.testjava.domain.entity.Product;
import br.com.blz.testjava.exception.ProductAreadyExistException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private static Map<Long, Product> resources = new HashMap<>();

    @Override
    public Set<Product> findAll() {
        return new HashSet<>(resources.values());
    }

    @Override
    public Product findBySku(Long sku) {

        Product product =  resources.get(sku);

        if(Objects.isNull(product)) {
            throw new ProductNotFoundException(sku);
        }

        return product;
    }

    @Override
    public void save(Product product) {

        Long sku = product.getSku();

        if(!Objects.isNull(resources.get(sku))) {
            throw new ProductAreadyExistException(sku);
        }

        resources.put(product.getSku(), product);
    }

    @Override
    public void update(Product product) {

        Long sku = product.getSku();
        Product productUpdate = resources.get(sku);

        if(Objects.isNull(productUpdate)) {
            throw new ProductNotFoundException(sku);
        }

        resources.put(product.getSku(), product);
    }

    @Override
    public void delete(Long sku) {

        Product product = resources.get(sku);

        if(Objects.isNull(product)) {
            throw new ProductNotFoundException(sku);
        }

        resources.remove(sku);
    }
}
