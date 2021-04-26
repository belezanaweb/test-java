package br.com.blz.testjava.domain.services

import br.com.blz.testjava.application.exception.DataConstraintException
import br.com.blz.testjava.domain.entities.Inventory
import br.com.blz.testjava.domain.entities.Product
import br.com.blz.testjava.domain.entities.Warehouse
import br.com.blz.testjava.domain.entities.WarehouseType
import br.com.blz.testjava.domain.repositories.ProductRepository
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable

internal class ProductServiceTest {

  private val productRepository: ProductRepository = mockk()

  @BeforeEach
  fun init() {
    clearMocks(productRepository)
  }

  @Test
  fun `Must thrown when try to save a product with sku already stored`() {
    val sku: Long = 1111111
    val warehouse = Warehouse("PR", 100, WarehouseType.ECOMMERCE)
    val inventory = Inventory(mutableListOf(warehouse))
    val product = Product(sku, "Product 1", inventory)

    every { productRepository.find(sku) } returns product

    val productService = ProductService(productRepository)

    assertThrows(DataConstraintException::class.java, Executable { productService.save(product) })
  }

  @Test
  fun `Must save a product when a sku is new`() {
    val sku: Long = 2222222
    val warehouse = Warehouse("PR", 100, WarehouseType.ECOMMERCE)
    val inventory = Inventory(mutableListOf(warehouse))
    val product = Product(sku, "Product 1", inventory)

    every { productRepository.find(sku) } returns null
    every { productRepository.save(product) } returns product

    val productService = ProductService(productRepository)

    productService.save(product)

    verify {
      productRepository.save(product)
    }

  }

  @Test
  fun `Must update a product when a sku is new`() {
    val sku: Long = 1111111
    val newSku: Long = 111222
    val warehouse = Warehouse("PR", 100, WarehouseType.ECOMMERCE)
    val inventory = Inventory(mutableListOf(warehouse))
    val newProduct = Product(newSku, "Product 1", inventory)

    every { productRepository.find(newSku) } returns null
    every { productRepository.update(newProduct) } returns newProduct

    val productService = ProductService(productRepository)
    productService.update(newProduct)

    verify {
      productRepository.update(newProduct)
    }
  }

  @Test
  fun `Must update a product when a sku is the same`() {
    val sku: Long = 1111111
    val warehouse = Warehouse("PR", 100, WarehouseType.ECOMMERCE)
    val inventory = Inventory(mutableListOf(warehouse))
    val updatedProduct = Product(sku, "Product 11", inventory)

    every { productRepository.update(updatedProduct) } returns updatedProduct

    val productService = ProductService(productRepository)
    productService.update(updatedProduct)

    verify {
      productRepository.update(updatedProduct)
    }
  }

}
