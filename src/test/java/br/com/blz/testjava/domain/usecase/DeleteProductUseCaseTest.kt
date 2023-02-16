package br.com.blz.testjava.domain.usecase

import br.com.blz.testjava.domain.exception.NotFoundException
import br.com.blz.testjava.domain.repository.ProductRepository
import br.com.blz.testjava.domain.repository.WarehouseRepository
import br.com.blz.testjava.factory.MockFactory
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class DeleteProductUseCaseTest {
  @Mock private lateinit var productRepository: ProductRepository
  @Mock private lateinit var warehouseRepository: WarehouseRepository

  @InjectMocks private lateinit var deleteProductUseCase: DeleteProductUseCase

  @Test
  fun `Should delete product`() {
    val product = MockFactory.createProduct()
    doReturn(product).`when`(productRepository).findById(product.sku.toString())

    assertDoesNotThrow {
      deleteProductUseCase.execute(product.sku.toString())
    }
  }

  @Test
  fun `Should throw not found exception when sku is not found`() {
    val product = MockFactory.createProduct()
    assertThrows<NotFoundException> {
      deleteProductUseCase.execute(product.sku.toString())
    }
  }

}
