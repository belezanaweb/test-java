package br.com.blz.testjava.infra.data;

import br.com.blz.testjava.core.domain.Inventory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryData implements Serializable {

    private Integer quantity;
    private List<WarehouseData> warehouses = new ArrayList<>();

    public static InventoryData from(final Inventory inventory) {
        return InventoryData.builder()
            .quantity(inventory.getQuantity())
            .warehouses(inventory.getWarehouses().stream().map(
                WarehouseData::from
                ).collect(Collectors.toList()))
            .build();
    }

    public Inventory toInventory(){
        return Inventory.builder()
            .quantity(this.quantity)
            .warehouses(this.warehouses.stream().map(
                WarehouseData::toWarehouse
                ).collect(Collectors.toList()))
            .build();
    }

}
