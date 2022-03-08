package br.com.blz.testjava.dto.out;

import br.com.blz.testjava.entity.Inventory;
import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.entity.Warehouse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InventoryDTOOutTest {

    @Test
    void quantity_should_not_be_zero() {
        Warehouse warehouse = new Warehouse("SP", 10, "ECOMMERCE");
        Inventory inventory = new Inventory();
        inventory.getWarehouses().add(warehouse);
        Product product = new Product(1, "dummy", inventory);
        ProductDTOOut productDTOOut = new ProductDTOOut(product);
        InventoryDTOOut inventoryDTOOut = productDTOOut.getInventory();
        Assertions.assertNotEquals(0, inventoryDTOOut.getQuantity());
    }

    @Test
    void quantity_should_be_zero() {
        Inventory inventory = new Inventory();
        Product product = new Product(1, "dummy", inventory);
        ProductDTOOut productDTOOut = new ProductDTOOut(product);
        InventoryDTOOut inventoryDTOOut = productDTOOut.getInventory();
        Assertions.assertEquals(0, inventoryDTOOut.getQuantity());
    }
}
