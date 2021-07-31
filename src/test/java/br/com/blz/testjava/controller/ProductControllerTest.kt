package br.com.blz.testjava.controller

import br.com.blz.testjava.enum.TypeWarehouse
import br.com.blz.testjava.model.Inventory
import br.com.blz.testjava.model.ProductRequest
import br.com.blz.testjava.model.ProductResponse
import br.com.blz.testjava.model.Warehouse
import br.com.blz.testjava.service.ProductService
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import kotlin.test.assertEquals

@ExtendWith(MockitoExtension::class)
class ProductControllerTest {

  @Mock
  lateinit var productService: ProductService

  @InjectMocks
  lateinit var productController: ProductController

  @Test
  fun `when to get product should return status code 200`() {
    val product = createProductResponse(1, "Florata 75ml")
    whenever(productService.getProduct(any())) doReturn product
    val response = productController.getSku(1)
    assertEquals(HttpStatus.OK, response.statusCode)
  }

  @Test
  fun `when product is not found should return status code 404`() {
    whenever(productService.getProduct(any())).doAnswer {throw Exception("erro")}
    val response = productController.getSku(1)
    assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
  }

  @Test
  fun `when create product should return status code 201`() {
    val product = createProductResponse(1, "Florata 75ml")
    val productRequest = createProductRequest(1, "Florata 75ml")
    whenever(productService.updateProduct(any(), any())) doReturn product
    val response = productController.updateSku(productRequest, 1)
    assertEquals(HttpStatus.CREATED, response.statusCode)
  }

  @Test
  fun `when error occurs during product update should return status code 404`() {
    val productRequest = createProductRequest(1, "Florata 75ml")
    whenever(productService.updateProduct(any(), any())).doAnswer {throw Exception("erro")}
    val response = productController.updateSku(productRequest, 1)
    assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
  }

  @Test
  fun `when product delete should return status code 204`() {
    whenever(productService.deleteProduct(any())) doReturn 1
    val response = productController.deleteSku(1)
    assertEquals(HttpStatus.NO_CONTENT, response.statusCode)
  }

  @Test
  fun `when error occurs during product delete should return status code 404`() {
    whenever(productService.deleteProduct(any())).doAnswer {throw Exception("erro")}
    val response = productController.deleteSku(1)
    assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
  }

  @Test
  fun `when product create should return status code 201`() {
    val product = createProductResponse(1, "Florata 75ml")
    val productRequest = createProductRequest(1, "Florata 75ml")
    whenever(productService.insertProduct(any())) doReturn product
    val response = productController.createSku(productRequest)
    assertEquals(HttpStatus.CREATED, response.statusCode)
  }

  @Test
  fun `when error occurs during product create should return status code 404`() {
    val product = createProductRequest(1, "Florata 75ml")
    whenever(productService.insertProduct(any())).doAnswer {throw Exception("erro")}
    val response = productController.createSku(product)
    assertEquals(HttpStatus.BAD_REQUEST, response.statusCode)
  }


  private fun createProductResponse(sku: Long, name: String): ProductResponse {
    val ecommerce = Warehouse(
      "Ecommerce",
      12,
      TypeWarehouse.ECOMMERCE
    )
    val inventory = Inventory(
      warehouses = listOf(ecommerce),
      quantity = 12
    )
    return ProductResponse(
      sku = sku,
      name = name,
      inventory = inventory,
      isMarketable = true
    )
  }

  private fun createProductRequest(sku: Long, name: String): ProductRequest {
    val ecommerce = Warehouse(
      "Ecommerce",
      12,
      TypeWarehouse.ECOMMERCE
    )
    val inventory = Inventory(
      warehouses = listOf(ecommerce)
    )
    return ProductRequest(
      sku = sku,
      name = name,
      inventory = inventory
    )
  }
}
