package br.com.blz.testjava.domain.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WarehouseTest {

  @Test
  public void calculateQuantityWarehouseListWithOneWarehouse() {
    List<Warehouse> warehouses = new ArrayList<>();
    warehouses.add(new Warehouse("SP", 12, "ECOMMERCE"));

    assertEquals(1, warehouses.size());
    assertEquals(12, warehouses.stream().mapToInt(Warehouse::getQuantity).sum());
  }

  @Test
  public void calculateQuantityWarehouseListWithTwoWarehouse() {
    List<Warehouse> warehouses = new ArrayList<>();
    warehouses.add(new Warehouse("SP", 12, "PHYSICAL_STORE"));
    warehouses.add(new Warehouse("SP", 5, "PHYSICAL_STORE"));

    assertEquals(2, warehouses.size());
    assertEquals(17, warehouses.stream().mapToInt(Warehouse::getQuantity).sum());
  }

}
