package br.com.blz.testjava.model

data class Product(
  var sku: Int,
  val name: String,
  val inventory: Inventory,
  var isMarketable: Boolean?
) {
  override fun equals(other: Any?): Boolean {
    return if (other is Product) {
      sku == other.sku
    } else false
  }

  override fun hashCode(): Int {
    var result = sku
    result = 31 * result + name.hashCode()
    return result
  }
}
