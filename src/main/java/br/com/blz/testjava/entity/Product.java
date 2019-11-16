package br.com.blz.testjava.entity;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {
    
    private long sku;
    private String name;
    private Inventory inventory;
    
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
    
    public Inventory getInventory() {
        return inventory;
    }
    
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        Product product = (Product) o;
        return sku == product.sku;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(sku);
    }
}
