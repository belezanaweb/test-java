package br.com.blz.testjava.product.api

import br.com.blz.testjava.product.ProductTestTemplates
import br.com.blz.testjava.product.api.dto.*
import br.com.blz.testjava.product.business.objects.WarehouseTypes
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.boot.test.web.client.getForEntity
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
    val product = ProductTestTemplates.createProductInputDTO()
    val response = restTemplate.postForEntity<String>(url, HttpEntity(product))

    assertEquals(HttpStatus.CREATED, response.statusCode)
  }

  @Test
  fun `Create same product twice shout returns an error`() {
    val product = createProduct(ProductTestTemplates.createProductInputDTO(2L, "Product 02"))
    var response = restTemplate.postForEntity<ProductAPISaveOutputErrorDTO>(url, HttpEntity(product))

    assertEquals(HttpStatus.BAD_REQUEST, response.statusCode)
  }

  @Test
  fun `Update product shoud returns 200`() {
    val product = createProduct(ProductTestTemplates.createProductInputDTO(3L, "Product 03"))
    val request = HttpEntity(ProductTestTemplates.createProductInputDTO(3L, "Product 003"))
    var response = restTemplate.exchange<ProductAPISaveOutputSuccessDTO>("$url/${product.sku}", HttpMethod.PUT, request)

    assertEquals(HttpStatus.OK, response.statusCode)
    assertTrue(response.body.success)
  }

  @Test
  fun `Get 404 on find not existent sku`() {
    val response = restTemplate.getForEntity<String>("$url/413221")

    assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
  }

  @Test
  fun `Get product details`() {
    val product = createProduct(ProductTestTemplates.createProductInputDTO(
      4L,
      "Product 4",
      ProductTestTemplates.createProductInputInventory(listOf(
        ProductTestTemplates.createProductInputWarehouse("SP", 12, WarehouseTypes.ECOMMERCE),
        ProductTestTemplates.createProductInputWarehouse("MOEMA", 3, WarehouseTypes.PHYSICAL_STORE)
      ))
    ))

    val response = restTemplate.getForEntity<ProductAPIGetOutputDTO>("$url/${product.sku}")

    assertEquals(HttpStatus.OK, response.statusCode)
    assertEquals(15, response.body.inventory!!.quantity)
    assertTrue(response.body.isMarketable!!)
  }

  @Test
  fun `Shoud return 200 on delete product`() {
    val product = createProduct(ProductTestTemplates.createProductInputDTO(4L, "Product 04"))
    var response = restTemplate.exchange<ProductAPIDeleteOutputSuccessDTO>("$url/${product.sku}", HttpMethod.DELETE)

    assertEquals(HttpStatus.OK, response.statusCode)
    assertTrue(response.body.success)
  }

  @Test
  fun `Shoud return 404 on delete not existent product`() {
    var response = restTemplate.exchange<ProductAPIDeleteOutputErrorDTO>("$url/4648", HttpMethod.DELETE)

    assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
    assertFalse(response.body.success)
    assertEquals(1, response.body.errors.size)
  }

  private fun createProduct(product: ProductAPISaveInputDTO) : ProductAPISaveInputDTO {
    val request = HttpEntity(product)
    restTemplate.postForEntity<String>(url, request)

    return product
  }
}
