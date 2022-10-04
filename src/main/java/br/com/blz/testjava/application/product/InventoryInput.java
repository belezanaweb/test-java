package br.com.blz.testjava.application.product;

import br.com.blz.testjava.domain.product.Warehouse;

import java.util.List;

public record InventoryInput(List<Warehouse> warehouses) {

    public static InventoryInput with(final List<Warehouse> warehouses) {
        return new InventoryInput(warehouses);
    }
}
