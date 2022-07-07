package br.com.blz.testkotlin.service

import br.com.blz.testkotlin.entity.ProductEntity
import br.com.blz.testkotlin.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl(val productRepository: ProductRepository) : ProductService {

  override fun saveProduct(product: ProductEntity): ProductEntity? {
    productRepository.save(product)
    return getProductBySku(product.sku)
  }

  override fun editProduct(product: ProductEntity): ProductEntity? {
    productRepository.update(product)
    return getProductBySku(product.sku)
  }

  override fun getProductBySku(sku: Long): ProductEntity? {
    var product = productRepository.findBySku(sku)
    if (product != null) {
      val sum =
        product.inventory.warehouses.map { warehouse -> warehouse.quantity }.reduceOrNull { sum, quantity -> sum + quantity }
      product.inventory.quantity = sum ?: 0
      product.isMarketable = product.inventory.quantity > 0
    }
    return product
  }

  override fun deleteProductBySku(sku: Long): Boolean {
    return productRepository.delete(sku)
  }

  override fun getAllProducts(): MutableList<ProductEntity>? {
    return productRepository.getAll()
  }
}
