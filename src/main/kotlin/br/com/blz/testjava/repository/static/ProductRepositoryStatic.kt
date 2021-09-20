package br.com.blz.testjava.repository.static

import br.com.blz.testjava.exception.ProductException
import br.com.blz.testjava.model.Inventory
import br.com.blz.testjava.model.Product
import br.com.blz.testjava.model.Warehouse
import br.com.blz.testjava.repository.ProductRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class ProductRepositoryStatic: ProductRepository {
  val products: MutableList<Product> = mutableListOf()

  init {
    var warehouse1 = Warehouse("SP", 12, "ECOMMERCE")
    var warehouse2 = Warehouse("MOEMA", 3, "PHYSICAL_STORE")

    var inventory = Inventory(listOf(warehouse1, warehouse2))

    var product = Product(43264, "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g", inventory)
    products.add(product)
  }

  override fun create(product: Product): Product {
    validate(product, true)

    products.add(product)

    return product
  }

  override fun find(sku: Long): Product {
    try {
      return products.find { it.sku == sku } !!
    } catch (e: NullPointerException) {
      throw ProductException("Produto não encontrado")
    }
  }

  override fun update(product: Product, sku: Long): Product {
    // delete product
    delete(sku !!)

    // updated product
    products.add(product)

    return product
  }

  override fun delete(sku: Long) {
    products.remove(find(sku))
  }

  /**
   * Pode ser usado a @annotations nos models
   * utilizado aqui para centralizar, pois não tem persistencia
   */
  private fun validate(product: Product, isNew: Boolean) {
    if(isNew) {
      var product = products.find { it.sku == product.sku }

      if(product != null) throw ProductException("Dois produtos são considerados iguais se os seus skus forem iguais")
    }
  }
}
