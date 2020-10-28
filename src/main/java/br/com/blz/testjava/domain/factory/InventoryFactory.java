package br.com.blz.testjava.domain.factory;

import br.com.blz.testjava.domain.Inventory;

public class InventoryFactory {
    private InventoryFactory() {
    }

    public static Inventory zeroQuantityInventory() {
        return new Inventory();
    }
}
