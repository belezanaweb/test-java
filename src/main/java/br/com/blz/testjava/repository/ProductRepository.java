package br.com.blz.testjava.repository;

import br.com.blz.testjava.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    Map<Long, Product> products = new HashMap<Long, Product>();

    public void save(Product product) {
        products.put(product.getSku(), product);

    }

    public void update(Product product) {
        products.replace(product.getSku(), product);
    }

    public void delete(Long sku) {
        products.remove(sku);
    }

    public Product findBySku(Long sku) {
        return products.get(sku);
    }

    public Boolean existsProduct(Long sku) {
        return products.containsKey(sku);
    }

    public List<Product> findAll() {
        List<Product> listOfValues = products.values().stream().collect(
            Collectors.toCollection(ArrayList::new));

        return listOfValues;
    }

}
