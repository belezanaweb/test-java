package br.com.blz.testjava.domain.entities

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class InventoryTest {

  @Test
  fun `An inventory with no quantity on warehouses must sum quantity of 0`() {
    val warehouses = mutableListOf<Warehouse>(
      Warehouse("PR", 0, WarehouseType.ECOMMERCE),
      Warehouse("SP", 0, WarehouseType.PHYSICAL_STORE)
    )
    val inventory = Inventory(warehouses)

    assertEquals(0, inventory.getQuantity())
  }

  @Test
  fun `An inventory with 5 quantity in all warehouses must sum quantity of 5`() {
    val warehouses = mutableListOf<Warehouse>(
      Warehouse("PR", 2, WarehouseType.ECOMMERCE),
      Warehouse("SP", 3, WarehouseType.PHYSICAL_STORE)
    )
    val inventory = Inventory(warehouses)

    assertEquals(5, inventory.getQuantity())
  }

  @Test
  fun `An inventory with 5 quantity in only one warehouse must sum quantity of 5`() {
    val warehouses = mutableListOf<Warehouse>(
      Warehouse("PR", 5, WarehouseType.ECOMMERCE),
      Warehouse("SP", 0, WarehouseType.PHYSICAL_STORE)
    )
    val inventory = Inventory(warehouses)

    assertEquals(5, inventory.getQuantity())
  }

}
