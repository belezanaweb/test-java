package br.com.blz.testjava.product;

import br.com.blz.testjava.application.dto.inventory.InventoryForm;

public class Product {

    private Long sku;
    private String name;
    private InventoryForm inventory;

    public Long getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public InventoryForm getInventory() {
        return inventory;
    }

    public Product(Long sku, String name, InventoryForm inventory) {
        this.sku = sku;
        this.name = name;
        this.inventory = inventory;
    }

}
