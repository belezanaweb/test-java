package br.com.blz.testkotlin.service

import br.com.blz.testkotlin.entity.InventoryEntity
import br.com.blz.testkotlin.entity.ProductEntity
import br.com.blz.testkotlin.entity.WarehouseEntity
import br.com.blz.testkotlin.enum.TypeEnum
import br.com.blz.testkotlin.repository.ProductRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class ProductServiceImplTest {

  @Autowired
  var productRepository: ProductRepository = ProductRepository()
  @Autowired
  private var productServiceImpl = ProductServiceImpl(productRepository)

  private var product = ProductEntity(
    sku = 43267,
    name = "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g",
    inventory = InventoryEntity(
      quantity = 0,
      warehouses = listOf(
        WarehouseEntity(locality = "SP", quantity = 12, type = TypeEnum.ECOMMERCE),
        WarehouseEntity(locality = "MOEMA", quantity = 13, type = TypeEnum.PHYSICAL_STORE)
      )
    ),
    isMarketable = true
  )

  private var productEdit = ProductEntity(
    sku = 43267,
    name = "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g",
    inventory = InventoryEntity(
      quantity = 4,
      warehouses = listOf(
        WarehouseEntity(locality = "SP", quantity = 1, type = TypeEnum.ECOMMERCE),
        WarehouseEntity(locality = "MOEMA", quantity = 3, type = TypeEnum.PHYSICAL_STORE)
      )
    ),
    isMarketable = true
  )

  @Test
  @Order(1)
  fun getAllProductsListEmpty() {
    assertTrue(getAllProducts()?.size == 0)
  }

  @Test
  @Order(2)
  fun createProduct() {
    assertTrue(saveProduct() !== null)
    assertTrue(getAllProducts()?.size!! > 0)
    assertTrue(getProductBySku()?.sku == product.sku)
  }

  @Test
  @Order(3)
  fun productInventory() {
    deleteProductBySku()
    assertTrue(saveProduct() !== null)
    assertTrue(getAllProducts()?.size!! > 0)
    var productInventory = getProductBySku()
    assertTrue(productInventory?.sku == product.sku)
    assertTrue(productInventory?.inventory?.quantity == product.inventory.warehouses.map { warehouse -> warehouse.quantity }.reduceOrNull { sum, quantity -> sum + quantity })
  }

  @Test
  @Order(4)
  fun updateProduct() {
    assertTrue(saveProduct() !== null)
    assertTrue(getProductBySku()?.sku == product.sku)
    assertTrue(getAllProducts()?.size!! > 0)
    assertEquals(editProduct()?.inventory?.quantity, productEdit.inventory.warehouses.map { warehouse -> warehouse.quantity }.reduceOrNull { sum, quantity -> sum + quantity })
    assertTrue(getProductBySku()?.sku == product.sku)
  }

  @Test
  @Order(5)
  fun deleteProduct() {
    assertTrue(saveProduct() !== null)
    assertTrue(getAllProducts()?.size!! > 0)
    assertTrue(getProductBySku()?.sku == product.sku)
    assertTrue(deleteProductBySku())
  }

  fun getAllProducts(): MutableList<ProductEntity>? {
    return productServiceImpl.getAllProducts()
  }

  fun saveProduct(): ProductEntity? {
    return productServiceImpl.saveProduct(product)
  }

  fun getProductBySku(): ProductEntity? {
    return productServiceImpl.getProductBySku(product.sku)
  }

  fun editProduct(): ProductEntity? {
    return productServiceImpl.editProduct(productEdit)
  }

  fun deleteProductBySku(): Boolean {
    return productServiceImpl.deleteProductBySku(product.sku)
  }
}
