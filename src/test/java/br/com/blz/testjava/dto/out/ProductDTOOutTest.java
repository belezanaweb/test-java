package br.com.blz.testjava.dto.out;

import br.com.blz.testjava.entity.Inventory;
import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.entity.Warehouse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ProductDTOOutTest {

    @Test
    void isMarketable_should_be_true() {
        Warehouse warehouse = new Warehouse("SP", 10, "ECOMMERCE");
        Inventory inventory = new Inventory();
        inventory.getWarehouses().add(warehouse);
        Product product = new Product(1, "dummy", inventory);
        ProductDTOOut productDTOOut = new ProductDTOOut(product);
        Assertions.assertTrue(productDTOOut.isMarketable());

    }

    @Test
    void isMarketable_should_be_false() {
        Inventory inventory = new Inventory();
        Product product = new Product(1, "dummy", inventory);
        ProductDTOOut productDTOOut = new ProductDTOOut(product);
        Assertions.assertFalse(productDTOOut.isMarketable());

    }

}
