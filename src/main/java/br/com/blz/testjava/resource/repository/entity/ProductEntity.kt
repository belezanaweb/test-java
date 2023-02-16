package br.com.blz.testjava.resource.repository.entity

import br.com.blz.testjava.domain.entity.Product
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class ProductEntity(
  @Id
  val sku: Int = 0,
  val name: String = "",
) {
  fun toDomain() = Product(
    sku = sku,
    name = name
  )
  companion object {
    fun fromDomain(product: Product) = ProductEntity(
      sku = product.sku,
      name = product.name
    )
  }
}
