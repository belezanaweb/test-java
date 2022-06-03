package br.com.blz.testjava.infra.data;

import br.com.blz.testjava.core.domain.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseData implements Serializable {

    private String locality;
    private Integer quantity;
    private WarehouseTypeData type;

    public static WarehouseData from(final Warehouse warehouse) {
        return WarehouseData.builder()
            .locality(warehouse.getLocality())
            .quantity(warehouse.getQuantity())
            .type(WarehouseTypeData.resolve(warehouse.getType()))
            .build();
    }

    public Warehouse toWarehouse(){
        return Warehouse.builder()
            .locality(this.locality)
            .quantity(this.quantity)
            .type(this.type.getDomainValue())
            .build();
    }

}
