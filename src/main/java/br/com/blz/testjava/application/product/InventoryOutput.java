package br.com.blz.testjava.application.product;

import br.com.blz.testjava.domain.product.Warehouse;

import java.util.List;

public record InventoryOutput(Long quantity, List<Warehouse> warehouses) {

    public static InventoryOutput with(final List<Warehouse> warehouses) {
        if (warehouses == null) {
            return new InventoryOutput(0L, null);
        }
        long quantity = warehouses.stream().map(Warehouse::getQuantity).mapToLong(Long::longValue).sum();
        return new InventoryOutput(quantity, warehouses);
    }
}
