package br.com.blz.testjava.repository.impl;

import br.com.blz.testjava.entity.Inventory;
import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.entity.Warehouse;
import br.com.blz.testjava.infrastructure.exception.ProductAreadyExistingException;
import br.com.blz.testjava.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private static Map<Long, Product> resources = new HashMap<>();

    @Override
    public Set<Product> findAll() {
        return new HashSet<>(resources.values());
    }

    @Override
    public void save(Product product) {

        Inventory inventory = product.getInventory();
        List<Warehouse> warehouses = inventory.getWarehouses();

        inventory.setQuantity(
            warehouses
                .stream()
                .mapToInt(Warehouse::getQuantity)
                .sum()
        );

        product.setInventory(inventory);

        if(!Objects.isNull(resources.get(product.getSku()))) {
            throw new ProductAreadyExistingException(product);
        }

        resources.put(product.getSku(), product);
    }
}
