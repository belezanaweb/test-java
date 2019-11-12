package br.com.blz.testjava.repository;

import br.com.blz.testjava.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductRepository {

    private Map<Long, Product> productMap = new HashMap<>();

    public Product save(Product product) {
        productMap.put(product.getSku(), product);
        return product;
    }

    public Product findBySku(Long sku){
        return productMap.get(sku);
    }
}
