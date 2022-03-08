package br.com.blz.testjava.entity;

import br.com.blz.testjava.dto.in.InventoryDTOIn;
import br.com.blz.testjava.dto.in.ProductDTOIn;

import java.util.Objects;

public class Product {

    private int sku;
    private String name;
    private Inventory inventory;

    public Product(){}

    public Product(int sku, String name, Inventory inventory) {
        this.sku = sku;
        this.name = name;
        this.inventory = inventory;
    }

    public Product (ProductDTOIn productDTOIn){
        this.setSku(productDTOIn.getSku());
        this.setName(productDTOIn.getName());
        this.setInventory(productDTOIn.getInventoryDTOIn());
    }

    public int getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return sku == product.sku && Objects.equals(name, product.name) && Objects.equals(inventory, product.inventory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, name, inventory);
    }

    private void setSku(int sku) {
        this.sku = sku;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setInventory(InventoryDTOIn inventoryDTOIn) {
        this.inventory = new Inventory(inventoryDTOIn);
    }
}
