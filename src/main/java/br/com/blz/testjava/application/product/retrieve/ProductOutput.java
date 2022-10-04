package br.com.blz.testjava.application.product.retrieve;

import br.com.blz.testjava.application.product.InventoryOutput;
import br.com.blz.testjava.domain.product.Product;

public record ProductOutput(Long sku, String name, InventoryOutput inventory, Boolean isMarketable) {

    public static ProductOutput from(final Product product) {
        InventoryOutput inventoryOutput = InventoryOutput.with(product.getWarehouses());
        return new ProductOutput(product.getId().getValue(), product.getName(), inventoryOutput, inventoryOutput.quantity() > 0L);
    }
}
