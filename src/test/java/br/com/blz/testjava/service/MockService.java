package br.com.blz.testjava.service;

import br.com.blz.testjava.controller.dto.InventoryDTO;
import br.com.blz.testjava.controller.dto.ProductDTO;
import br.com.blz.testjava.controller.dto.WarehouseDTO;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.WarehouseType;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Component
public class MockService {

    public ProductDTO createProduct() {
        return this.createProduct(null);
    }

    public ProductDTO createProduct(final Long sku) {
        final ProductDTO result = new ProductDTO();
        result.setSku(Optional.ofNullable(sku).orElse(new Random().nextLong()));
        result.setName("Product Name");
        result.setInventory(this.createInventory());

        return result;
    }

    public InventoryDTO createInventory() {
        final InventoryDTO result = new InventoryDTO();
        result.add(this.createWarehouse(WarehouseType.ECOMMERCE, 100, "SP"));
        result.add(this.createWarehouse(WarehouseType.PHYSICAL_STORE, 50, "RJ"));

        return result;
    }

    public WarehouseDTO createWarehouse(WarehouseType type, int quantity, String locality) {
        final WarehouseDTO result = new WarehouseDTO();
        result.setType(type);
        result.setQuantity(quantity);
        result.setLocality(locality);

        return result;
    }
}
