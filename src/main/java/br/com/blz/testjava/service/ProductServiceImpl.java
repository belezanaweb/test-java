package br.com.blz.testjava.service;

import br.com.blz.testjava.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private List<Product> products = new ArrayList<>();

    @Override
    public Product save(Product product) throws Exception {
        Product product1 = this.find(product.getSku());
        if(product1 != null) {
            throw new Exception("O produto com esse sku já está cadastrado.");
        }
        products.add(product);
        return product;
    }

    @Override
    public Product find(String sku) {
        Product product = null;
        if (products != null && !products.isEmpty()) {
            product = products.stream().filter(p -> p.getSku().equals(sku)).findAny().get();
        }
        return product;
    }

    @Override
    public Product update(String sku, Product product) {
        this.remove(sku);
        products.add(product);
        return product;
    }

    @Override
    public void remove(String sku) {
        Product product = this.find(sku);
        if(product != null) {
            products.remove(product);
        }
    }

    public List<Product> findAll() {
        return products;
    }
}
