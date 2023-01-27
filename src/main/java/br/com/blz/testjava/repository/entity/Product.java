package br.com.blz.testjava.repository.entity;

import java.io.Serializable;

public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    private long sku;
    private String name;
    private Inventory inventory;

    public Product(long sku, String name) {
        this.sku = sku;
        this.name = name;

    }

    public Product() {
    }

    public long getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setSku(long sku) {
        this.sku = sku;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public int hashCode() {
        final long prime = 31;
        long result = 1;
        result = prime * result + sku;
        return (int) result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (sku != other.sku)
            return false;
        return true;
    }

}
