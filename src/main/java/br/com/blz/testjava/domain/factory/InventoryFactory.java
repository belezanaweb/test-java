package br.com.blz.testjava.domain.factory;

import br.com.blz.testjava.domain.model.Inventory;

public class InventoryFactory {
    private InventoryFactory() {
    }

    public static Inventory zeroQuantityInventory() {
        return new Inventory();
    }
}