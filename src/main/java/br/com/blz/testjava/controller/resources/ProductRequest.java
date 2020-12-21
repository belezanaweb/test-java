package br.com.blz.testjava.controller.resources;

import com.google.common.base.Objects;

import java.util.Set;

public class ProductRequest {
    private Long sku;
    private String name;
    private Set<WarehousesResource> warehousesResponse;

    public ProductRequest() {
    }

    public ProductRequest(Long sku, String name, Set<WarehousesResource> warehousesResponse) {
        this.sku = sku;
        this.name = name;
        this.warehousesResponse = warehousesResponse;
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

    public Set<WarehousesResource> getWarehousesResponse() {
        return warehousesResponse;
    }

    public void setWarehousesResponse(Set<WarehousesResource> warehousesResponse) {
        this.warehousesResponse = warehousesResponse;
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
            ", warehousesResponse=" + warehousesResponse +
            '}';
    }
}
