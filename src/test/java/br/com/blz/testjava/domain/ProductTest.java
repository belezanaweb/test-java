package br.com.blz.testjava.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class ProductTest {

    Product product;

    @Before
    public void beforeEach() {
        product = new Product();
        product.setInventory(new Inventory());
    }

    @Test
    public void noMarketableProductTest() {
        product.setInventory(null);
        assertThat("Product should not be marketable if have no inventory",
                product.isIsMarketable(), CoreMatchers.is(false));
    }

    @Test
    public void notMarketableNoWarehousesTest() {
        assertThat("Product should not be marketable if have inventory have no warehouses",
                product.isIsMarketable(), CoreMatchers.is(false));
    }

    @Test
    public void productInventoryQuantityWithoutWarehousesTest() {
        assertThat("Product inventory should have a correct quantity (0) without warehouses",
                product.getInventory().getQuantity(), CoreMatchers.is(0L));
    }

    @Test
    public void marketableProductTest() {

        Warehouse warehouse = new Warehouse();
        warehouse.setQuantity(10);
        List<Warehouse> warehouseList = Arrays.asList(warehouse);
        product.getInventory().setWarehouses(warehouseList);

        assertThat("Product should be marketable if have inventory with warehouses",
                product.isIsMarketable(), CoreMatchers.is(true));
    }

    @Test
    public void notMarketableProductWithEmptyWarehousesTest() {

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setQuantity(0);
        Warehouse warehouse2 = new Warehouse();
        warehouse2.setQuantity(0);
        
        product.getInventory()
                .setWarehouses(Arrays.asList(warehouse1, warehouse2));

        assertThat("Product should not be marketable if have warehouses with zero quantity",
                product.isIsMarketable(), CoreMatchers.is(false));
    }

    @Test
    public void productInventoryQuantityTest() {

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setQuantity(10);
        Warehouse warehouse2 = new Warehouse();
        warehouse2.setQuantity(15);
        List<Warehouse> warehouseList = new ArrayList<>();
        warehouseList.add(warehouse1);
        warehouseList.add(warehouse2);
        product.getInventory().setWarehouses(warehouseList);

        assertThat("Product inventory should have a correct quantity",
                product.getInventory().getQuantity(), CoreMatchers.is(25L));
    }

}
