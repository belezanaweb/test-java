package br.com.blz.testjava.model

import br.com.blz.testjava.enum.ProductType
import org.assertj.core.util.Lists
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductTest {
  private lateinit var warehouse:  Warehouse
  private lateinit var warehouse2: Warehouse
  private lateinit var product: Product

  @BeforeEach
  fun init() {
    warehouse2 = Warehouse("Rio de Janeiro", 5, ProductType.ECOMMERCE)
    warehouse = Warehouse("São Paulo", 2, ProductType.ECOMMERCE)
    product = Product(12345L, "Perfume", Inventory(Lists.list(warehouse, warehouse2)))
  }

  @Test
  fun isMarketableProductTrueTest() {
    Assertions.assertTrue(product.isMarketable())
  }

  @Test
  fun isMarketableProductFalseTest() {
    product.inventory.warehouses.map { warehouse -> warehouse.quantity = 0 }
    Assertions.assertFalse(product.isMarketable())
  }
}
