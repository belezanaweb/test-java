package br.com.blz.testjava.database;

import br.com.blz.testjava.model.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BelezaNaWebDatabase {
    private static final Map<Long, Product> DATABASE = new HashMap();

    public static Optional<Product> findById(final Long sku) {
        final Product product = DATABASE.get(sku);
        return Optional.ofNullable(product);
    }

    public static void save(final Product product) {
        DATABASE.put(product.getSku(), product);
    }

    public static void deleteById(final Long sku) {
        DATABASE.remove(sku);
    }

    public static void clear() {
        DATABASE.clear();
    }
}
