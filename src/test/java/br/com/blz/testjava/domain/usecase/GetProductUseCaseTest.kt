package br.com.blz.testjava.domain.usecase

import br.com.blz.testjava.domain.exception.NotFoundException
import br.com.blz.testjava.domain.repository.ProductRepository
import br.com.blz.testjava.domain.repository.WarehouseRepository
import br.com.blz.testjava.factory.MockFactory
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExtendWith(MockitoExtension::class)
class GetProductUseCaseTest {
  @Mock private lateinit var productRepository: ProductRepository
  @Mock private lateinit var warehouseRepository: WarehouseRepository

  @InjectMocks private lateinit var getProductUseCase: GetProductUseCase

  @Test
  fun `Should get product with correct values for isMarketable and quantity`() {
    val product = MockFactory.createProduct()
    val warehouse = MockFactory.createWarehouse()
    doReturn(product).`when`(productRepository).findById(product.sku.toString())
    doReturn(listOf(warehouse, warehouse)).`when`(warehouseRepository).findBySku(product.sku)

    val response = getProductUseCase.execute(product.sku.toString())

    assertTrue { response.isMarketable }
    assertEquals(10, response.inventory.quantity)
  }

  @Test
  fun `Should get product with correct values for isMarketable and quantity when is not marketable`() {
    val product = MockFactory.createProduct()
    val warehouse = MockFactory.createWarehouse(0)
    doReturn(product).`when`(productRepository).findById(product.sku.toString())
    doReturn(listOf(warehouse)).`when`(warehouseRepository).findBySku(product.sku)

    val response = getProductUseCase.execute(product.sku.toString())

    assertFalse { response.isMarketable }
    assertEquals(0, response.inventory.quantity)
  }

  @Test
  fun `Should throw not found exception when sku is not found`() {
    assertThrows<NotFoundException> {
      getProductUseCase.execute("000")
    }
  }

}
