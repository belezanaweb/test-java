package br.com.blz.testjava.product.business

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

@RunWith(SpringRunner::class)
class ProductBusinessTest {
  @Test
  fun `Avoid save duplicaded sku`() {
    val productBusiness = ProductBusiness()
    val product = Product(1L, "Teste", ProductInventory(listOf()))

    productBusiness.create(product)
    assertThrows<ProductSkuDuplicatedException> { productBusiness.create(product) }
  }

  @Test
  fun `Throw exception updating not existent product`() {
    val product = Product(2L, "Teste", ProductInventory(listOf()))

    assertThrows<ProductNotFoundException> { ProductBusiness().update(product) }
  }

  @Test
  fun `Update product name`() {
    val product = Product(3L, "Teste", ProductInventory(listOf()))

    ProductBusiness().create(product)
    ProductBusiness().update(with(product) { Product(sku, "Nome alterado", inventory) })
    val changedProduct = ProductRepository.get(product.sku)

    assertEquals("Nome alterado", changedProduct!!.name)
  }
}
