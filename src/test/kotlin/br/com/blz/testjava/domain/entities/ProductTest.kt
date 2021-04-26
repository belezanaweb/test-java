package br.com.blz.testjava.domain.entities

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class ProductTest {

  @Test
  fun `A Product is marketable when it is available on any warehouses`() {
    val warehouses = mutableListOf<Warehouse>(
      Warehouse("PR", 2, WarehouseType.ECOMMERCE),
      Warehouse("SP", 3, WarehouseType.PHYSICAL_STORE)
    )
    val inventory = Inventory(warehouses)
    val product = Product(43264, "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g", inventory)

    assertTrue(product.isMarketable())
  }

  @Test
  fun `A Product not is marketable when it is not available any warehouses`() {
    val warehouses = mutableListOf<Warehouse>(
      Warehouse("PR", 0, WarehouseType.ECOMMERCE),
      Warehouse("SP", 0, WarehouseType.PHYSICAL_STORE)
    )
    val inventory = Inventory(warehouses)
    val product = Product(43264, "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g", inventory)

    assertFalse(product.isMarketable())
  }

}
