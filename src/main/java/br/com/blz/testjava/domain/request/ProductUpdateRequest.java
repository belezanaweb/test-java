package br.com.blz.testjava.domain.request;

import org.hibernate.validator.constraints.NotBlank;

public class ProductUpdateRequest {

    @NotBlank
    private String name;
    private InventoryRequest inventory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InventoryRequest getInventory() {
        return inventory;
    }

    public void setInventory(InventoryRequest inventory) {
        this.inventory = inventory;
    }
}
