package br.com.blz.testjava.application.product.update;

import br.com.blz.testjava.application.product.InventoryInput;

public record UpdateProductCommand(Long sku, String name, InventoryInput inventory) {

    public static UpdateProductCommand with(final Long sku, final String name, final InventoryInput inventory) {
        return new UpdateProductCommand(sku, name, inventory);
    }
}
