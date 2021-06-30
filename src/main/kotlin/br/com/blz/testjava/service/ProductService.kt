package br.com.blz.testjava.service

import br.com.blz.testjava.model.Product
import br.com.blz.testjava.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {


  fun create(product: Product): Product {
    this.incrementQuantity(product)
    this.checkMarketable(product)

    return productRepository.save(product)
  }

  fun delete(sku: Long) {
    if (productRepository.findById(sku) == null) throw Exception("Product not exists!")
    productRepository.delete(sku)
  }

  fun findAll(): List<Product> = productRepository.findAll()

  private fun incrementQuantity(product: Product) {
    val quantity = product.inventory!!.warehouses!!.sumOf { it.quantity }
    product.inventory!!.quantity = quantity
  }

  private fun checkMarketable(product: Product) {
    product.isMarketable = product.inventory!!.quantity > 0
  }

  fun update(product: Product): Product {
    this.incrementQuantity(product)
    this.checkMarketable(product)
    return this.productRepository.update(product)
  }

  fun findById(sku: Long): Product =
    productRepository.findById(sku) ?: throw Exception("Product not exists!")

}
