package br.com.blz.testjava.controller.dto;

import java.util.*;

public class InventoryDTO {
    private final List<WarehouseDTO> warehouses = new ArrayList<>();

    public void add(final WarehouseDTO item) {
        warehouses.add(item);
    }

    public void addAll(final Collection<WarehouseDTO> items) {
        warehouses.addAll(items);
    }

    public List<WarehouseDTO> getWarehouses() {
        return Collections.unmodifiableList(warehouses);
    }

    public Integer getQuantity() {
        return warehouses.stream()
            .reduce(
                0,
                (partialQuantity, warehouse) -> partialQuantity + warehouse.getQuantity(),
                Integer::sum);
    }
}
