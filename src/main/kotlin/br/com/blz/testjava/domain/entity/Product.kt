package br.com.blz.testjava.domain.entity

data class Product(
    val sku: Int,
    val name: String,
    val inventory: Inventory,
) {

    var isMarketable: Boolean = false
      get() = inventory.quantity > 0

    fun equals(other: Product): Boolean {
        return this.sku == other.sku
    }

}
