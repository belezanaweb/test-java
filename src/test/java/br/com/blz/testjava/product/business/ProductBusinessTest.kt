package br.com.blz.testjava.product.business

import br.com.blz.testjava.product.ProductTestTemplates
import br.com.blz.testjava.product.business.objects.Product
import br.com.blz.testjava.product.business.objects.ProductInventory
import br.com.blz.testjava.product.exception.ProductNotFoundException
import br.com.blz.testjava.product.exception.ProductSkuDuplicatedException
import br.com.blz.testjava.product.repository.ProductRepository
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@RunWith(SpringRunner::class)
class ProductBusinessTest {

  private val productBusiness = ProductBusiness()
  @Test
  fun `Avoid save duplicaded sku`() {
    val product = ProductTestTemplates.createProduct()

    productBusiness.create(product)
    assertThrows<ProductSkuDuplicatedException> { productBusiness.create(product) }
  }

  @Test
  fun `Throw exception updating not existent product`() {
    val product = ProductTestTemplates.createProduct(2L, "Product 2")

    assertThrows<ProductNotFoundException> { ProductBusiness().update(product) }
  }

  @Test
  fun `Update product name`() {
    ProductBusiness().create(ProductTestTemplates.createProduct(3L, "Product 3"))
    ProductBusiness().update(ProductTestTemplates.createProduct(3L, "Product 03"))

    val changedProduct = ProductRepository.get(3L)

    assertEquals("Product 03", changedProduct!!.name)
  }

  @Test
  fun `Remove product`() {
    ProductBusiness().create(ProductTestTemplates.createProduct(4L, "Product 3"))
    assertNotNull(ProductRepository.get(4L))

    ProductBusiness().delete(4L)
    assertNull(ProductRepository.get(4L))
  }
}
