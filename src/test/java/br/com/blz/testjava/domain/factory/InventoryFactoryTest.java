package br.com.blz.testjava.domain.factory;

import br.com.blz.testjava.domain.Inventory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InventoryFactoryTest {

    @Test
    void shouldCreateAnEmptyQuantityInventory() {
        Inventory inventory = InventoryFactory.zeroQuantityInventory();
        assertEquals(0, inventory.getQuantity());
    }
}
