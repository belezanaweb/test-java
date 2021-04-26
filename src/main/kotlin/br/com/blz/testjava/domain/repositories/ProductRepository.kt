package br.com.blz.testjava.domain.repositories

import br.com.blz.testjava.application.exception.NotFoundException
import br.com.blz.testjava.domain.entities.Inventory
import br.com.blz.testjava.domain.entities.Product
import br.com.blz.testjava.domain.entities.Warehouse
import org.springframework.stereotype.Repository


@Repository
class ProductRepository {

  companion object {
    private val products = mutableListOf<Product>()
  }

  fun save(product: Product): Product {
    products.add(
      cloneProduct(product)
    )
    return product
  }

  fun update(product: Product): Product {
    val savedProduct = products.find { _product: Product -> _product.sku == product.sku }
      ?: throw NotFoundException("Product not found with sku ${product.sku}")

    savedProduct.apply {
      this.inventory = cloneInventory(product.inventory)
      this.name = product.name
      this.sku = product.sku
    }

    return product
  }

  fun delete(product: Product): Boolean {
    val savedProduct = products.find { _product: Product -> _product.sku == product.sku }
      ?: throw NotFoundException("Product not found with sku ${product.sku}")

    return products.removeIf { it.sku == product.sku }
  }

  fun find(sku: Long): Product? {
    return products.find { _product: Product -> _product.sku == sku }
  }

  fun findAll(): List<Product> {
    return products.map { product ->
      cloneProduct(product)
    }
  }

  private fun cloneProduct(product: Product) = Product(
    sku = product.sku,
    name = product.name,
    inventory = cloneInventory(product.inventory)
  )

  private fun cloneInventory(inventory: Inventory) = Inventory(
    inventory.warehouses
      .map { warehouse -> Warehouse(warehouse.locality, warehouse.quantity, warehouse.type) }
  )

}

