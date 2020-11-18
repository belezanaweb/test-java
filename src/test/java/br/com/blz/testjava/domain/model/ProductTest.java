package br.com.blz.testjava.domain.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductTest {

  @Test
  public void checkMarketableWithQuantityGreatterZero() {
    List<Warehouse> warehouses = new ArrayList<>();
    warehouses.add(new Warehouse("SP", 12, "ECOMMERCE"));

    Inventory inventory = new Inventory();
    inventory.setWarehouses(warehouses);

    Product product = new Product();
    product.setInventory(inventory);

    assertEquals(true, product.isMarketable());
  }

  @Test
  public void checkMarketableWithoutWarehouses() {
    List<Warehouse> warehouses = new ArrayList<>();

    Inventory inventory = new Inventory();
    inventory.setWarehouses(warehouses);

    Product product = new Product();
    product.setInventory(inventory);

    assertEquals(false, product.isMarketable());
  }

}
