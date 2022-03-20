package br.com.blz.testjava.repository

import br.com.blz.testjava.Exception.NotExistsException
import br.com.blz.testjava.model.Product
import org.springframework.stereotype.Repository

@Repository
class ProductInMemoryRepository(
  private var storedData: MutableSet<Product> = mutableSetOf()
) : ProductRepository {

  override fun save(product: Product): Boolean {
    return this.storedData.add(product)
  }

  override fun update(product: Product): Product {
    val storedProducts = storedData.filter { storedProduct -> storedProduct.sku == product.sku }
    if (storedProducts.isNotEmpty()) {
      val storedProduct = storedProducts.first()
      storedProduct.name = product.name
      storedProduct.inventory = product.inventory
      return product
    }
    throw NotExistsException("Product sku not found")
  }

  override fun findBySku(sku: Long): Product {
    val filteredProducts = storedData.filter { storedProduct -> storedProduct.sku == sku }
    return if (filteredProducts.isNotEmpty()) filteredProducts.first()
      else throw NotExistsException("Product sku not found")
  }

  override fun delete(sku: Long): Boolean {
    val filteredProduct = findBySku(sku)
    return storedData.remove(filteredProduct)
  }
}
