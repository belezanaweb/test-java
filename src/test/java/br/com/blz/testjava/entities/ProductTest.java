package br.com.blz.testjava.entities;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    void isMarketableTest() {
        var product = new Product();
        assertFalse(product.getIsMarketable());
        product.setInventory(createInventory());
        assertTrue(product.getIsMarketable());
    }

    private Inventory createInventory() {
        var inventory = new Inventory();
        inventory.setWarehouses(createWarehouses());

        return inventory;
    }

    private List<Warehouse> createWarehouses(){
        var warehouses = new ArrayList<Warehouse>();
        var warehouse1 = new Warehouse();
        warehouse1.setQuantity(2);
        var warehouse2 = new Warehouse();
        warehouse2.setQuantity(3);
        warehouses.add(warehouse1);
        warehouses.add(warehouse2);
        return warehouses;

    }
}
