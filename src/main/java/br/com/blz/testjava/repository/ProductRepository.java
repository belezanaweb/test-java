package br.com.blz.testjava.repository;

import br.com.blz.testjava.domain.Product;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Defines operations to memory database.
 */
@Component
public class ProductRepository {

    private static final Map<Integer, Product> DATABASE = new HashMap<>();

    /**
     * It saves a {@link Product}.
     * @param product - {@link Product}
     * @return Product - {@link Product}
     */
    public Product save(Product product) {

        synchronized (DATABASE) {
            return DATABASE.put(product.getSku(), product);
        }
    }

    /**
     * It deletes a {@link Product} by sku.
     * @param sku {@link Product} identity.
     * @return true if removed false otherwise.
     */
    public boolean delete(Integer sku) {
        synchronized (DATABASE) {
            return DATABASE.remove(sku) != null;
        }
    }

    /**
     * It tries to find a {@link Product} by sku.
     * @param sku - {@link Product} identity.
     * @return - {@link Product} or null.
     */
    public Product find(Integer sku) {
        synchronized (DATABASE) {
            return DATABASE.get(sku);
        }
    }
}
