package br.com.blz.testjava.domain.usecase

import br.com.blz.testjava.domain.entity.UpdateProduct
import br.com.blz.testjava.domain.exception.NotFoundException
import br.com.blz.testjava.domain.repository.ProductRepository
import br.com.blz.testjava.domain.repository.WarehouseRepository
import br.com.blz.testjava.factory.MockFactory
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExtendWith(MockitoExtension::class)
class UpdateProductUseCaseTest {
  @Mock private lateinit var productRepository: ProductRepository
  @Mock private lateinit var warehouseRepository: WarehouseRepository

  @InjectMocks private lateinit var updateProductUseCase: UpdateProductUseCase

  @Test
  fun `Should update product with correct values for isMarketable and quantity`() {
    val product = MockFactory.createProduct()
    val warehouse = MockFactory.createWarehouse()
    doReturn(product).`when`(productRepository).findById(product.sku.toString())
    doReturn(product).`when`(productRepository).save(product)
    doReturn(warehouse).`when`(warehouseRepository).save(warehouse, product.sku)

    val response = updateProductUseCase.execute(
      UpdateProduct(product.sku, product.name, listOf(
        warehouse,
        warehouse,
      ))
    )

    assertTrue { response.isMarketable }
    assertEquals(10, response.inventory.quantity)
    Mockito.verify(warehouseRepository, Mockito.times(1)).deleteBySku(product.sku)
  }

  @Test
  fun `Should update product with correct values for isMarketable and quantity when is not marketable`() {
    val product = MockFactory.createProduct()
    val warehouse = MockFactory.createWarehouse(quantity = 0)
    doReturn(product).`when`(productRepository).findById(product.sku.toString())
    doReturn(product).`when`(productRepository).save(product)
    doReturn(warehouse).`when`(warehouseRepository).save(warehouse, product.sku)

    val response = updateProductUseCase.execute(
      UpdateProduct(product.sku, product.name, listOf(
        warehouse,
        warehouse,
      ))
    )

    assertFalse { response.isMarketable }
    assertEquals(0, response.inventory.quantity)
  }

  @Test
  fun `Should throw not found exception when sku is not found`() {
    val product = MockFactory.createProduct()
    val warehouse = MockFactory.createWarehouse(quantity = 0)

    assertThrows<NotFoundException> {
      updateProductUseCase.execute(
        UpdateProduct(product.sku, product.name, listOf(
          warehouse,
          warehouse,
        ))
      )
    }
    Mockito.verify(warehouseRepository, Mockito.times(0)).deleteBySku(product.sku)
  }

}
