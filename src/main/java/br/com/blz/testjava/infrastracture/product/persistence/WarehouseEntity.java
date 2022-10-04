package br.com.blz.testjava.infrastracture.product.persistence;

import br.com.blz.testjava.domain.product.Warehouse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WarehouseEntity {

    private String locality;
    private long quantity;
    private String type;

    public static WarehouseEntity from(final Warehouse warehouse) {
        return WarehouseEntity.builder().locality(warehouse.getLocality()).quantity(warehouse.getQuantity()).type(warehouse.getType()).build();
    }

    public Warehouse toAggregate() {
        return Warehouse.with(locality, quantity, type);
    }
}
