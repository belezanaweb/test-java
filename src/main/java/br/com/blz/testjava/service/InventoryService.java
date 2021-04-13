package br.com.blz.testjava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.entity.Inventory;
import br.com.blz.testjava.repository.InventoryRepository;
import dto.InventoryDTO;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository repository;

    public List<Inventory> findAll() {
        return repository.findAll();
    }

    public Inventory findByProduct(Long product) {
        return repository.findOne(product);
    }

    public Inventory save(Inventory inventory) {
        return repository.save(inventory);
    }

    public void deleteInventory(Long sku) {
        repository.delete(sku);
    }
}
