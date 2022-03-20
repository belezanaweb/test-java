package br.com.blz.testjava.model

import br.com.blz.testjava.enum.ProductType
import org.assertj.core.util.Lists
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class InventoryTest {
  private lateinit var warehouse:  Warehouse
  private lateinit var warehouse2: Warehouse
  private lateinit var inventory: Inventory

  @BeforeEach
  fun init() {
    warehouse2 = Warehouse("Rio de Janeiro", 5, ProductType.ECOMMERCE)
    warehouse = Warehouse("São Paulo", 2, ProductType.ECOMMERCE)
    inventory = Inventory(Lists.list(warehouse, warehouse2))
  }

  @Test
  fun inventoryTest() {
    val quantity = inventory.getQuantity()
    Assertions.assertEquals(quantity, 7)
  }
}
