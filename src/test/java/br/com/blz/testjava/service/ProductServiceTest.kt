package br.com.blz.testjava.service

import br.com.blz.testjava.exception.ProductExistingException
import br.com.blz.testjava.exception.ProductNotFoundException
import br.com.blz.testjava.model.Inventory
import br.com.blz.testjava.model.Product
import br.com.blz.testjava.model.Warehouses
import br.com.blz.testjava.repository.ProductRepository

import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
internal class ProductServiceTest {

    private val productService = ProductService()

    @Test
    fun save() {
        val product = createProduct()

        productService.save(product)
        assertThrows<ProductExistingException> { productService.save(product) }
    }

    @Test
    fun update() {
        ProductService().save(createProduct(3L, "STYLETTO"))
        ProductService().update(createProduct(3L, "STYLETTO"))

        val alterProduct = ProductRepository.findById(3L)

        assertEquals("STYLETTO", alterProduct!!.name)
    }

    @Test
    fun remove() {
        ProductService().save(createProduct(3L, "STYLETTO"))
        assertNotNull(ProductRepository.findById(3L))

        ProductService().delete(3L)
        assertNull(ProductRepository.findById(3L))
    }

  @Test
  fun productException() {
    val product = createProduct(4L, "MALBEC")
    assertThrows<ProductNotFoundException> { ProductService().update(product) }
  }

  private fun createWarehouse(locality: String = "PR", quantity: Int = 1, type: String = "ECOMMERCE") : Warehouses = Warehouses(locality, quantity, type)

  private fun createInventory(quantity: Int = 0, warehouses: List<Warehouses> = listOf(createWarehouse())): Inventory = Inventory(quantity, warehouses)

  private fun createProduct(sku: Long = 1L, name: String = "MAKE B. COCOA", inventory: Inventory = createInventory(), isMarketable: Boolean = false): Product = Product(sku, name, inventory, isMarketable)

}
