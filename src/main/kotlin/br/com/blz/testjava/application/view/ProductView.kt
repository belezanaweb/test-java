package br.com.blz.testjava.application.view

data class ProductView(
    val sku: Int,
    val name: String,
    val inventory: InventoryView,
    val isMarketable: Boolean
)
