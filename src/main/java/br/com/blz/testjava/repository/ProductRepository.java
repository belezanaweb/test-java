package br.com.blz.testjava.repository;

import br.com.blz.testjava.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductRepository {

    private Map<Long, Product> products = new HashMap<Long, Product>();

    public void addProduct(Product product) {
        products.put(product.getSku(), product);
    }

    public Product findProductBySku(Long sku) {
        return products.get(sku);
    }

    public void deleteProduct(Long sku) {
        products.remove(sku);
    }

    public void updateProduct(Long sku, Product product) {
        products.replace(sku, product);
    }
}
