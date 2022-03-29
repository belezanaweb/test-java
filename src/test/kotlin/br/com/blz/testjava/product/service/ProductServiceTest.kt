package br.com.blz.testjava.product.service

import br.com.blz.testjava.exception.DomainBusinessException
import br.com.blz.testjava.exception.EntityNotFoundException
import br.com.blz.testjava.product.mapper.ProductMapper
import br.com.blz.testjava.product.repository.IProductRepository
import br.com.blz.testjava.product.service.provider.ProductProvider
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import java.util.Optional.empty
import java.util.Optional.of

@ExtendWith(MockitoExtension::class)
internal class ProductServiceTest {

  @InjectMocks
  lateinit var productService: ProductService

  @Mock
  lateinit var productRepository: IProductRepository

  @Test
  fun `should throw error when trying to save existing product`() {
    val productInDTO = ProductProvider.createInDto()

    `when`(productRepository.existsBySku(productInDTO.sku)).thenReturn(true)

    assertThatExceptionOfType(DomainBusinessException::class.java).isThrownBy {
      productService.create(productInDTO)
    }.withMessageContaining("Não foi possível criar, produto já existente")
  }

  @Test
  fun `must create product`() {
    val productInDTO = ProductProvider.createInDto()

    `when`(productRepository.existsBySku(productInDTO.sku)).thenReturn(false)

    productService.create(productInDTO)

    verify(productRepository, times(1)).save(any())
  }

  @Test
  fun `must edit product by sku`() {
    val productInDTO = ProductProvider.createInDto()

    productService.editBySku(productInDTO.sku, productInDTO)

    verify(productRepository, times(1)).save(any())
  }

  @Test
  fun `must find product by sku`() {
    val product = ProductProvider.createEntity()

    `when`(productRepository.findBySku(product.sku)).thenReturn(of(product))

    val productOutDTO = productService.findBySku(product.sku)

    assertEquals(ProductMapper.toOutDto(product), productOutDTO)
  }

  @Test
  fun `should throw error when not finding product by sku`() {
    val sku = 43264L

    `when`(productRepository.findBySku(sku)).thenReturn(empty())

    assertThatExceptionOfType(EntityNotFoundException::class.java).isThrownBy {
      productService.findBySku(sku)
    }.withMessageContaining("Produto não encontrado")
  }

  @Test
  fun `must delete product by sku`() {
    val sku = 43264L

    productService.deleteBySku(sku)

    verify(productRepository, times(1)).deleteBySku(sku)
  }
}
