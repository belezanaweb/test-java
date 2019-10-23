package br.com.blz.testjava.controller.dto;

import java.util.Optional;

public class ProductDTO {
    private Long sku;
    private String name;
    private InventoryDTO inventory = new InventoryDTO();

    public Long getSku() {
        return sku;
    }

    public void setSku(Long sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InventoryDTO getInventory() {
        return inventory;
    }

    public void setInventory(InventoryDTO inventory) {
        this.inventory = inventory;
    }

    public Boolean getIsMarketable() {
        final Integer quantity = Optional.ofNullable(inventory)
            .orElse(new InventoryDTO())
            .getQuantity();

        return quantity.intValue() > 0;
    }
}
