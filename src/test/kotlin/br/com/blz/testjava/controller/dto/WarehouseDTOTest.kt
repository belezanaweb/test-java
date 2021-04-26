package br.com.blz.testjava.controller.dto

import br.com.blz.testjava.domain.entities.Warehouse
import br.com.blz.testjava.domain.entities.WarehouseType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class WarehouseDTOTest {

  @Test
  fun `Must convert a Warehouse entity to WarehouseDTO`() {
    val warehouse = Warehouse("SP", 10, WarehouseType.ECOMMERCE)
    val warehouseDTO = WarehouseDTO(warehouse)

    assertEquals(warehouseDTO.locality, warehouse.locality)
    assertEquals(warehouseDTO.quantity, warehouse.quantity)
    assertEquals(warehouseDTO.type, warehouse.type)
  }

}
