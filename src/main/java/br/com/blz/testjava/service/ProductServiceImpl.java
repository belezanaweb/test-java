package br.com.blz.testjava.service;

import br.com.blz.testjava.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    List<Product> products = new ArrayList<>();

    public Product save(Product product) {
        products.add(product);
        return product;
    }

    @Override
    public Product find(String sku) {
        return products.stream().filter(p -> p.getSku().equals(sku)).findAny().get();
    }

    @Override
    public Product update(String sku, Product product) {
        return null;
    }

    @Override
    public void remove(String sku) {

    }

    public List<Product> findAll() {
        return products;
    }
}
