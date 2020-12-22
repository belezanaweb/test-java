package br.com.blz.testjava.controller.resources;

import br.com.blz.testjava.persistence.entity.Product;
import com.google.common.base.Objects;

public class ProductResponse {

    private Long sku;
    private String name;
    private InventoryResponse inventory;
    private Boolean isMarketable;

    public ProductResponse() {
    }

    public ProductResponse(Long sku, String name, InventoryResponse inventoryResponse, Boolean isMarketable) {
        this.sku = sku;
        this.name = name;
        this.inventory = inventoryResponse;
        this.isMarketable = isMarketable;
    }

    public ProductResponse(Product product) {
        this.sku = product.getSku();
        this.name = product.getName();
        this.inventory = new InventoryResponse(product.getWarehouses());
        this.isMarketable = inventory.getQuantity() > 0;
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

    public InventoryResponse getInventoryResponse() {
        return inventory;
    }

    public void setInventoryResponse(InventoryResponse inventoryResponse) {
        this.inventory = inventoryResponse;
    }

    public Boolean getMarketable() {
        return isMarketable;
    }

    public void setMarketable(Boolean marketable) {
        isMarketable = marketable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductResponse that = (ProductResponse) o;
        return Objects.equal(sku, that.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(sku);
    }

    @Override
    public String toString() {
        return "ProductResponse{" +
            "sku=" + sku +
            ", name='" + name + '\'' +
            ", inventoryResponse=" + inventory +
            ", isMarketable=" + isMarketable +
            '}';
    }
}
