package br.com.blz.testjava.resource.repository.impl

import br.com.blz.testjava.domain.entity.Product
import br.com.blz.testjava.domain.repository.ProductRepository
import br.com.blz.testjava.resource.repository.entity.ProductEntity
import br.com.blz.testjava.resource.repository.jpa.JpaProductRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
open class ProductRepositoryImpl (
  private val jpa: JpaProductRepository
) : ProductRepository {
  override fun save(product: Product): Product {
    return jpa.save(ProductEntity.fromDomain(product)).toDomain()
  }

  override fun findById(sku: String): Product? {
    return jpa.findByIdOrNull(sku.toInt())?.toDomain()
  }

  override fun existsById(sku: Int): Boolean {
    return jpa.existsById(sku)
  }

  override fun deleteById(sku: Int) {
    jpa.deleteById(sku)
  }

}
