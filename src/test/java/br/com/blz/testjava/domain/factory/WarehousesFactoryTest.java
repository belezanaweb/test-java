package br.com.blz.testjava.domain.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.blz.testjava.domain.model.Warehouse;

class WarehousesFactoryTest {

    @Test
    void shouldCreateAZeroQuantityWarehouseListWithOneWarehouse() {
        List<Warehouse> warehouses = WarehousesFactory.zeroQuantityWarehouse();

        assertFalse(warehouses.isEmpty());
        assertEquals(1, warehouses.size());
        assertEquals(0, warehouses.stream().mapToInt(Warehouse::getQuantity).sum());
    }
}
