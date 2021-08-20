package br.com.blz.testjava.adapter_out.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Builder
@Data
public class ProductEntity {

    private Long sku;

    private String name;

    private InventoryEntity inventoryEntity;

    private Boolean isMarketable;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(sku, that.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku);
    }
}
