package br.com.blz.testjava.domain.repositories

import br.com.blz.testjava.application.exception.NotFoundException
import br.com.blz.testjava.domain.entities.Inventory
import br.com.blz.testjava.domain.entities.Product
import br.com.blz.testjava.domain.entities.Warehouse
import br.com.blz.testjava.domain.entities.WarehouseType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable

internal class ProductRepositoryTest {

  @Test
  fun `Must find all products`() {
    val productRepository: ProductRepository = ProductRepository()
    val productsCount = productRepository.findAll().size
    val warehouse = Warehouse("PR", 100, WarehouseType.ECOMMERCE)
    val inventory = Inventory(mutableListOf(warehouse))
    productRepository.save(Product(name = "Product 1", inventory = inventory))
    productRepository.save(Product(name = "Product 2", inventory = inventory))
    productRepository.save(Product(name = "Product 3", inventory = inventory))

    assertEquals(productsCount + 3, productRepository.findAll().size)
  }

  @Test
  fun `Must find a Product by a sku`() {
    val productRepository: ProductRepository = ProductRepository()
    val warehouse = Warehouse("PR", 100, WarehouseType.ECOMMERCE)
    val inventory = Inventory(mutableListOf(warehouse))
    val product = productRepository.save(Product(name = "Product 4", inventory = inventory))

    assertNotNull(productRepository.find(product.sku!!))
  }

  @Test
  fun `Try to find a Product with an incorrect sku must return null`() {
    val productRepository: ProductRepository = ProductRepository()

    assertNull(productRepository.find(112233))
  }

  @Test
  fun `Must insert a product and return one with and sku`() {
    val productRepository: ProductRepository = ProductRepository()
    val inventory = Inventory(mutableListOf(Warehouse("PR", 100, WarehouseType.ECOMMERCE)))
    val product = Product(name = "Product 5", inventory = inventory)
    val saveProduct = productRepository.save(product)

    assertNotNull(saveProduct.sku)
  }

  @Test
  fun `Must update the name of a product`() {
    val productRepository: ProductRepository = ProductRepository()
    val inventory = Inventory(mutableListOf(Warehouse("PR", 100, WarehouseType.ECOMMERCE)))
    val product = productRepository.save(Product(name = "Product 6", inventory = inventory))

    product.name += "_"
    productRepository.update(product)

    val updateProduct = productRepository.find(product.sku!!)
    assertEquals(product.name, updateProduct?.name)
  }


  @Test
  fun `Must update the warehouses of an inventory`() {
    val productRepository: ProductRepository = ProductRepository()
    val warehouse = Warehouse("PR", 100, WarehouseType.ECOMMERCE)
    val inventory = Inventory(mutableListOf(warehouse))
    val product = productRepository.save(Product(name = "Product 7", inventory = inventory))

    val newInventory = Inventory(
      mutableListOf(
        warehouse.apply { quantity = 80 },
        Warehouse("PR", 105, WarehouseType.PHYSICAL_STORE)
      )
    )
    product.inventory = newInventory
    productRepository.update(product)

    val updateProduct = productRepository.find(product.sku!!)!!

    assertEquals(product.inventory.warehouses, updateProduct.inventory.warehouses)
  }

  @Test
  fun `Must delete a product`() {
    val productRepository: ProductRepository = ProductRepository()
    val warehouse = Warehouse("PR", 100, WarehouseType.ECOMMERCE)
    val inventory = Inventory(mutableListOf(warehouse))
    val product = productRepository.save(Product(name = "Product 8", inventory = inventory))

    assertNotNull(productRepository.find(product.sku!!))
    productRepository.delete(product)
    assertNull(productRepository.find(product.sku!!))
  }

  @Test
  fun `Must throw an exception when try to update a product with an invalid sku`() {
    val productRepository: ProductRepository = ProductRepository()
    val warehouse = Warehouse("PR", 100, WarehouseType.ECOMMERCE)
    val inventory = Inventory(mutableListOf(warehouse))
    val product = Product(sku = 112233, name = "Product 9", inventory = inventory)

    assertThrows(NotFoundException::class.java, Executable { productRepository.update(product) })
  }

  @Test
  fun `Must throw an exception when try to delete a product with an invalid sku`() {
    val productRepository: ProductRepository = ProductRepository()
    val warehouse = Warehouse("PR", 100, WarehouseType.ECOMMERCE)
    val inventory = Inventory(mutableListOf(warehouse))
    val product = Product(sku = 112233, name = "Product 9", inventory = inventory)

    assertThrows(NotFoundException::class.java, Executable { productRepository.delete(product) })
  }

}
