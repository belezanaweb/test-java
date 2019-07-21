package br.com.blz.testjava.domain.api.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ReplaceProductRequest {

    @NotBlank
    private String name;
    @Valid
    @NotNull
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
