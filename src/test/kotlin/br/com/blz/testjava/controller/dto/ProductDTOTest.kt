package br.com.blz.testjava.controller.dto

import br.com.blz.testjava.domain.entities.Inventory
import br.com.blz.testjava.domain.entities.Product
import br.com.blz.testjava.domain.entities.Warehouse
import br.com.blz.testjava.domain.entities.WarehouseType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ProductDTOTest {

  @Test
  fun `Must convert a Product with isMarketable true`() {
    val warehouses = listOf(Warehouse("SP", 10, WarehouseType.ECOMMERCE))
    val inventory = Inventory(warehouses)
    val product = Product(123456, "Product 1", inventory)

    val productDTO = ProductDTO(product)


    assertEquals(productDTO.sku, product.sku)
    assertEquals(productDTO.name, product.name)
    assertEquals(productDTO.isMarketable, product.isMarketable())
  }

  @Test
  fun `Must convert a Product with isMarketable false`() {
    val warehouses = listOf(Warehouse("SP", 0, WarehouseType.ECOMMERCE))
    val inventory = Inventory(warehouses)
    val product = Product(123456, "Product 1", inventory)

    val productDTO = ProductDTO(product)


    assertEquals(productDTO.sku, product.sku)
    assertEquals(productDTO.name, product.name)
    assertEquals(productDTO.isMarketable, product.isMarketable())
  }

}
