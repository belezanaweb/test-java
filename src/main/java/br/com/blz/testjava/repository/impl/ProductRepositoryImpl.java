package br.com.blz.testjava.repository.impl;

import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private static Map<Long, Product> resources = new HashMap<>();


    @Override
    public Set<Product> findAll() {
        return null;
    }

    @Override
    public void save(Product product) {

    }
}
