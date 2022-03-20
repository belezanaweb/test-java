package br.com.blz.testjava.rest

import br.com.blz.testjava.Exception.NotExistsException
import br.com.blz.testjava.enum.ProductType
import br.com.blz.testjava.rest.dto.InventoryDto
import br.com.blz.testjava.rest.dto.ProductDto
import br.com.blz.testjava.rest.dto.WarehouseDto
import br.com.blz.testjava.service.ProductService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ProductControllerTest {
  private lateinit var productService: ProductService
  private lateinit var productController: ProductController
  private lateinit var productDto: ProductDto

  @BeforeEach
  fun init() {
    productService = Mockito.mock(ProductService::class.java)
    productController = ProductController(productService)
    productDto = ProductDto(
      12345L, "creme",
      InventoryDto(
        mutableListOf(WarehouseDto("São paulo", 2, ProductType.PHYSICAL_STORE))
      ),
      true
    )
  }

  @Test
  fun postProduct_ShouldReturnOkTest() {
    Mockito.`when`(productService.createProduct(productDto.toProductModel())).thenReturn(true)
    Assertions.assertEquals(productController.postProduct(productDto), ResponseEntity(productDto, HttpStatus.OK));
  }

  @Test
  fun postProduct_ShouldReturnErrorTest() {
    Mockito.`when`(productService.createProduct(productDto.toProductModel())).thenReturn(false)
    Assertions.assertEquals(productController.postProduct(productDto),
      ResponseEntity<ProductDto>(HttpStatus.INTERNAL_SERVER_ERROR));
  }

  @Test
  fun updateProduct_shouldSaveTest() {
    Mockito.`when`(productService.updateProduct(productDto.toProductModel()))
      .thenReturn(productDto.toProductModel())
    val responseEntity = productController.updateProduct(productDto)
    Assertions.assertEquals(HttpStatus.OK, responseEntity.statusCode)
  }

  @Test
  fun updateProduct_shouldNotSaveTest() {
    Mockito.`when`(productService.updateProduct(productDto.toProductModel()))
      .thenThrow(NotExistsException("Product sku not found"))
    val responseEntity = productController.updateProduct(productDto)
    Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.statusCode)
  }

  @Test
  fun findProduct_shouldReturnOkTest() {
    Mockito.`when`(productService.findBySku(productDto.sku))
      .thenReturn(productDto.toProductModel())
    val responseEntity = productController.findProductBySku(productDto.sku)
    Assertions.assertEquals(HttpStatus.OK, responseEntity.statusCode)
  }

  @Test
  fun findProduct_shouldThrowNotExistsExceptionTest() {
    Mockito.`when`(productService.findBySku(productDto.sku))
      .thenThrow(NotExistsException("Product sku not found"))
    val responseEntity = productController.findProductBySku(productDto.sku)
    Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.statusCode)
    Assertions.assertEquals("Product sku not found", responseEntity.body)
  }

  @Test
  fun deleteProduct_shouldReturnOkTest() {
    Mockito.`when`(productService.deleteProduct(productDto.sku)).thenReturn(true)
    val responseEntity = productController.deleteProductBySKu(productDto.sku)
    Assertions.assertEquals(HttpStatus.OK, responseEntity.statusCode)
  }

  @Test
  fun deleteProduct_ShouldReturnErrorTest() {
    Mockito.`when`(productService.deleteProduct(productDto.sku)).thenReturn(false)
    val responseEntity = productController.deleteProductBySKu(productDto.sku)
    Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.statusCode)
  }

  @Test
  fun deleteProduct_ShouldThrowNotExistsExceptionTest() {
    Mockito.`when`(productService.deleteProduct(productDto.sku)).thenThrow(NotExistsException("Product sku not found"))
    val responseEntity = productController.deleteProductBySKu(productDto.sku)
    Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.statusCode)
    Assertions.assertEquals("Product sku not found", responseEntity.body)
  }


}
