package br.com.blz.testjava.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository repository;

    public Inventory findByProduct(Long product) {
        return repository.findOne(product);
    }

    public Inventory save(Inventory inventory) {
        return repository.save(inventory);
    }
}
