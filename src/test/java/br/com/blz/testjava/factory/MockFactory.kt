package br.com.blz.testjava.factory

import br.com.blz.testjava.domain.entity.Product
import br.com.blz.testjava.domain.entity.Warehouse
import br.com.blz.testjava.domain.entity.WarehouseType

class MockFactory {
  companion object {
    fun createProduct() = Product(10, "Produto teste")
    fun createWarehouse(quantity: Int = 5) = Warehouse("SP", quantity, WarehouseType.ECOMMERCE)
  }
}
