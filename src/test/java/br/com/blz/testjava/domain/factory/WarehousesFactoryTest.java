package br.com.blz.testjava.domain.factory;

import br.com.blz.testjava.domain.Warehouse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class WarehousesFactoryTest {

    @Test
    void shouldCreateAZeroQuantityWarehouseListWithOneWarehouse() {
        List<Warehouse> warehouses = WarehousesFactory.zeroQuantityWarehouse();

        assertFalse(warehouses.isEmpty());
        assertEquals(1, warehouses.size());
        assertEquals(0, warehouses.stream().mapToInt(Warehouse::getQuantity).sum());
    }
}
