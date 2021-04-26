package br.com.blz.testjava.controller.dto

import br.com.blz.testjava.domain.entities.Inventory
import br.com.blz.testjava.domain.entities.Warehouse
import br.com.blz.testjava.domain.entities.WarehouseType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class InventoryDTOTest {

  @Test
  fun `Must convert an Inventory with quantity 10`() {
    val warehouses = listOf(Warehouse("SP", 10, WarehouseType.ECOMMERCE))
    val inventory = Inventory(warehouses)

    val inventoryDTO = InventoryDTO(inventory)

    assertEquals(inventoryDTO.quantity, inventory.getQuantity())
    assertEquals(inventoryDTO.warehouses.size, inventory.warehouses.size)
  }

}
