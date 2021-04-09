package br.com.blz.testjava.product.business

import br.com.blz.testjava.product.business.objects.Product
import br.com.blz.testjava.product.business.objects.ProductInventory
import br.com.blz.testjava.product.exception.ProductSkuDuplicatedException
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertEquals

@RunWith(SpringRunner::class)
class ProductBusinessTest {
  @Test
  fun `Avoid save duplicaded sku`() {
    val productBusiness = ProductBusiness()
    val product = Product(1L, "Teste", ProductInventory(listOf()))

    productBusiness.save(product)
    assertThrows<ProductSkuDuplicatedException> { productBusiness.save(product) }
  }
}
