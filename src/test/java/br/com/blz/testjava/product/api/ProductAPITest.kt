package br.com.blz.testjava.product.api

import br.com.blz.testjava.product.api.dto.*
import br.com.blz.testjava.product.business.objects.WarehouseTypes
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductAPITest {

  @LocalServerPort
  val localServerPort: Int = 0

  private lateinit var restTemplate: TestRestTemplate
  private lateinit var url: String

  @BeforeAll
  fun setup() {
    url = "http://localhost:$localServerPort/products"
    restTemplate = TestRestTemplate()
  }

  @Test
  fun `Create a product should returns 201`() {
    val product = ProductAPISaveInputDTO(1L, "Product 01", ProductInventoryAPISaveInputDTO(listOf()))
    val request = HttpEntity(product)
    val response = restTemplate.postForEntity<String>(url, request)

    assertEquals(HttpStatus.CREATED, response.statusCode)
  }

  @Test
  fun `Create same product twice shout returns an error`() {
    val product = ProductAPISaveInputDTO(2L, "Product 01", ProductInventoryAPISaveInputDTO(listOf()))
    val request = HttpEntity(product)

    var response = restTemplate.postForEntity<ProductAPISaveOutputDTOSuccess>(url, request)
    var response2 = restTemplate.postForEntity<ProductAPISaveOutputDTOError>(url, request)

    assertEquals(HttpStatus.CREATED, response.statusCode)
    assertEquals(HttpStatus.BAD_REQUEST, response2.statusCode)
  }

  @Test
  fun `Update product shoud returns 200`() {
    val product = ProductAPISaveInputDTO(3L, "Product 01", ProductInventoryAPISaveInputDTO(listOf()))
    val request = HttpEntity(product)

    restTemplate.postForEntity<String>(url, request)

    ProductAPISaveInputDTO(product.sku, "Teste", ProductInventoryAPISaveInputDTO(listOf(ProductWarehouseAPISaveInputDTO("PR", 1, WarehouseTypes.ECOMMERCE.toString()))))
    var response = restTemplate.exchange<ProductAPISaveInputDTO>("$url/${product.sku}", HttpMethod.PUT, request)

    assertEquals(HttpStatus.OK, response.statusCode)
  }
}
