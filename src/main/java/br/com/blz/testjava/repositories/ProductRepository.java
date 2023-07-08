package br.com.blz.testjava.repositories;

import br.com.blz.testjava.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private List<Product> products = new ArrayList<>();

    public void save(Product product) {
        products.add(product);
    }

    public Optional<Product> findById(Integer sku) {
        return products.stream()
            .filter(p -> p.getSku().equals(sku))
            .findFirst();
    }

    public void deleteById(Integer sku) {
        products.removeIf(p -> p.getSku().equals(sku));
    }
}
