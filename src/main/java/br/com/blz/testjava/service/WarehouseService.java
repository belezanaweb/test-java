package br.com.blz.testjava.service;

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.domain.Warehouse;
import br.com.blz.testjava.dto.InventoryDto;
import br.com.blz.testjava.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository repository;

    @Transactional
    public void removeBySku(Product product) {
        repository.deleteAllByProduct(product);
    }

    public void createWarehouse(Product product, InventoryDto inventory) {
        inventory.getWarehouses()
                .stream()
                .forEach(warehouseDto -> {
                    repository.save(new Warehouse(product, warehouseDto));
                });
    }

    public List<Warehouse> getWarehouses(Product product) {
        return repository.findByProduct(product);

    }
}
