package br.com.blz.testjava.application.model

data class ProductModel(
    val sku: Int,
    val name: String,
    val inventory: InventoryModel,
    val isMarketable: Boolean
)
