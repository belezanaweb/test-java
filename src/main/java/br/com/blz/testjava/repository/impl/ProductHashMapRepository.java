package br.com.blz.testjava.repository.impl;

import br.com.blz.testjava.model.entity.Product;
import br.com.blz.testjava.repository.ProductRepository;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Repository;

@Repository
public class ProductHashMapRepository implements ProductRepository {

    public final ConcurrentHashMap<Long, Product> products = new ConcurrentHashMap<>();

    public List<Product> findAll() {
        return products.values().stream().collect(Collectors.toList());
    }

    public Product find(@NotNull Long sku) {
        return products.get(sku);
    }

    public Product delete(@NotNull Long sku) {
        return products.remove(sku);
    }

    public Product save(@NotNull Product product) {
        boolean saved = products.putIfAbsent(product.getSku(), product) == null;
        return saved ? product : null;
    }

    public Product update(@NotNull Product product) {
        boolean updated = products.replace(product.getSku(), product) != null;
        return updated ? product : null;
    }

    public void clear() {
        products.clear();
    }


}
