package br.com.blz.testjava.service.impl;

import br.com.blz.testjava.domain.Inventory;
import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.domain.Warehouse;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product create(Product product) {
        Inventory inventory = new Inventory.Builder()
            .withWarehouses(product.getInventory().getWarehouses())
            .withQuantity(calculateInventoryQuantity(product.getInventory().getWarehouses()))
            .build();
        product.setInventory(inventory);
        product.setMarketable(inventory.getQuantity() > 0 ? true : false);
        return repository.save(product);
    }

    private Integer calculateInventoryQuantity(List<Warehouse> warehouses) {
        return warehouses.stream()
            .map(w -> w.getQuantity())
            .collect(Collectors.summingInt(Integer::intValue));
    }
}
