package br.com.blz.testjava.domain.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.blz.testjava.domain.model.Inventory;

class InventoryFactoryTest {

    @Test
    void shouldCreateAnEmptyQuantityInventory() {
        Inventory inventory = InventoryFactory.zeroQuantityInventory();
        assertEquals(0, inventory.getQuantity());
    }
}
