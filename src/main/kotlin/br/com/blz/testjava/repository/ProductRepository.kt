package br.com.blz.testjava.repository

import br.com.blz.testjava.model.Product
import org.springframework.stereotype.Repository

@Repository
class ProductRepository() {
  private var products: MutableList<Product> = mutableListOf()
  private var idCounter: Long = 1L

  @Synchronized
  fun createID(sku: Long?): Long = sku ?: idCounter++

  fun save(product: Product): Product {

    product.sku = this.createID(product.sku)

    if (findById(product.sku!!) != null) throw Exception("Duplicate key!")

    this.products.add(product)

    return product
  }

  fun update(product: Product): Product {
    if (findById(product.sku!!) != null) this.delete(product.sku!!) else throw Exception(("Product not exists!"))
    this.products.add(product)
    return product
  }

  fun findById(sku: Long): Product? =
    this.products.firstOrNull() { it -> it.sku == sku }

  fun findAll(): List<Product> = this.products

  fun delete(sku: Long) {
    this.products.removeIf { it -> it.sku == sku }
  }

}
