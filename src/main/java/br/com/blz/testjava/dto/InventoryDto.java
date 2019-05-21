package br.com.blz.testjava.dto;

import br.com.blz.testjava.domain.Warehouse;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryDto {

    private int quantity;

    private List<WarehouseDto> warehouses;

    public InventoryDto() {

    }

    public InventoryDto(List<Warehouse> warehouses) {
        this.quantity = warehouses.stream()
            .parallel()
            .mapToInt(warehouse -> warehouse.getQuantity())
            .sum();

        this.warehouses = warehouses.stream()
            .parallel()
            .map(warehouse -> new WarehouseDto(warehouse))
            .collect(Collectors.toList());
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<WarehouseDto> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<WarehouseDto> warehouses) {
        this.warehouses = warehouses;
    }
}
