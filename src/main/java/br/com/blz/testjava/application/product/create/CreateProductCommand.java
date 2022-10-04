package br.com.blz.testjava.application.product.create;

import br.com.blz.testjava.application.product.InventoryInput;

public record CreateProductCommand(Long sku, String name, InventoryInput inventory) {

    public static CreateProductCommand with(final Long sku, final String name, final InventoryInput inventory) {
        return new CreateProductCommand(sku, name, inventory);
    }
}
