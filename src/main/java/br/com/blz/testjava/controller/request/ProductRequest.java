package br.com.blz.testjava.controller.request;

import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

public class ProductRequest implements Serializable {
    
    @NotNull
    private long sku;
    
    @NotBlank
    private String name;
    
    @Valid
    private InventoryRequest inventory = new InventoryRequest();
    
    public long getSku() {
        return sku;
    }
    
    public void setSku(long sku) {
        this.sku = sku;
    }
    
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
