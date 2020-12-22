package br.com.blz.testjava.controller.resources;

import com.google.common.base.Objects;

public class ProductRequest {
    private Long sku;
    private String name;
    private InventoryRequest inventory;

    public ProductRequest() {
    }

    public ProductRequest(Long sku, String name, InventoryRequest inventory) {
        this.sku = sku;
        this.name = name;
        this.inventory = inventory;
    }

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

    public InventoryRequest getInventory() {
        return inventory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductRequest request = (ProductRequest) o;
        return Objects.equal(sku, request.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(sku);
    }

    @Override
    public String toString() {
        return "ProductRequest{" +
            "sku=" + sku +
            ", name='" + name + '\'' +
            ", inventory=" + inventory +
            '}';
    }
}
