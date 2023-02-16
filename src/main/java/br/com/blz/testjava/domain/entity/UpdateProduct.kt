package br.com.blz.testjava.domain.entity

data class UpdateProduct (
  val sku: Int,
  val name: String,
  val inventory: List<Warehouse>
) {
  fun toProduct() = Product(
    sku = sku,
    name = name
  )
}
