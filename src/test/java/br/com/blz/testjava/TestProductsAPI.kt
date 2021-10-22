package br.com.blz.testjava

import br.com.blz.testjava.product.dto.ProductInventoryRequestDTO
import br.com.blz.testjava.product.dto.ProductRequestDTO
import br.com.blz.testjava.product.dto.WareHouseRequestDTO
import br.com.blz.testjava.product.exceptions.ProductExistentException
import br.com.blz.testjava.product.exceptions.ProductNotFoundException
import br.com.blz.testjava.product.service.ProductService
import br.com.blz.testjava.types.WarehouseType
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertEquals

@RunWith(SpringRunner::class)
@SpringBootTest
class TestProductsAPI() {

  @Autowired
  lateinit var productService: ProductService

  @Test
  fun `verifica se cadastra um produto`() {
    val product = createProdutoPadraoComEstoque()
    productService.create(product)
  }

  @Test(expected = ProductExistentException::class)
  fun `verifica se lanca exception produto existente`() {
    val skuDuplicado = 3333L
    val product = createProdutoPadraoComEstoque().copy(sku = skuDuplicado)
    productService.create(product)

    val outroProduto = createProdutoPadraoComEstoque().copy(sku = skuDuplicado, name = "OUtro nome")
    productService.create(outroProduto)
  }

  @Test
  fun `verifica se atualiza um produto`() {
    val skuAtualizado = 222L
    val product = createProdutoPadraoComEstoque().copy(sku = skuAtualizado)
    productService.create(product)

    val productParaAtualizar = product.copy(name = "Atualizou o nome")
    productService.update(skuAtualizado, productParaAtualizar)

    val recuperado = productService.getBySku(skuAtualizado)
    assertEquals(productParaAtualizar.sku, recuperado.sku)
    assertEquals(productParaAtualizar.name, recuperado.name)
    assertEquals(productParaAtualizar.inventory.warehouses.size, recuperado.inventory.warehouses.size)
  }

  @Test(expected = ProductNotFoundException::class)
  fun `verifica se atualiza um produto nao existente`() {
    val sku = 6666L
    val product = createProdutoPadraoComEstoque().copy(sku = sku)
    productService.create(product)

    val skuInexistente = 1L
    val productParaAtualizar = product.copy(name = "Atualizou o nome", sku = skuInexistente)
    productService.update(skuInexistente, productParaAtualizar)
  }


  @Test
  fun `verifica quantidade e isMarketable`() {
    val sku = 9991L
    val product = createProdutoPadraoComEstoque().copy(sku = sku)
    productService.create(product)

    val recuperado = productService.getBySku(sku)
    assertEquals(15, recuperado.inventory.quantity)
    assertEquals(true, recuperado.isMarketable)


    val sku2 = 9992L
    val product2 = createProdutoPadraoSemEstoque().copy(sku = sku2)
    productService.create(product2)

    val recuperado2 = productService.getBySku(sku2)
    assertEquals(0, recuperado2.inventory.quantity)
    assertEquals(false, recuperado2.isMarketable)
  }


  @Test
  fun `verifica se exclui um produto`() {
    val skuAtualizado = 2442L
    val product = createProdutoPadraoComEstoque().copy(sku = skuAtualizado)
    productService.create(product)

    productService.deleteProduct(sku = skuAtualizado)
  }

  @Test(expected = ProductNotFoundException::class)
  fun `verifica se exclui um produto Inexistente`() {
    val skuAtualizado = 2443L
    val product = createProdutoPadraoComEstoque().copy(sku = skuAtualizado)
    productService.create(product)

    val skuInexistente = 108883L
    productService.deleteProduct(sku = skuInexistente)
  }

  @Test(expected = ProductNotFoundException::class)
  fun `verifica se recupera um produto Inexistente`() {
    val skuInexistente = 10666L
    productService.getBySku(skuInexistente)
  }

  private fun createProdutoPadraoComEstoque() = ProductRequestDTO(
    sku = 123,
    name = "Produto de teste",
    inventory = ProductInventoryRequestDTO(
      warehouses = listOf(
        WareHouseRequestDTO(
          locality = "SP",
          quantity = 10,
          type = WarehouseType.ECOMMERCE
        ),

        WareHouseRequestDTO(
          locality = "MOEMA",
          quantity = 5,
          type = WarehouseType.PHYSICAL_STORE
        )
      )
    )
  )

  private fun createProdutoPadraoSemEstoque() = ProductRequestDTO(
    sku = 1267,
    name = "Produto de teste",
    inventory = ProductInventoryRequestDTO(
      warehouses = listOf(
        WareHouseRequestDTO(
          locality = "SP",
          quantity = 0,
          type = WarehouseType.ECOMMERCE
        ),

        WareHouseRequestDTO(
          locality = "MOEMA",
          quantity = 0,
          type = WarehouseType.PHYSICAL_STORE
        )
      )
    )
  )
}
