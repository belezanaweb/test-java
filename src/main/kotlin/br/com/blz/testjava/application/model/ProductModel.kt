package br.com.blz.testjava.application.model

data class ProductModel(
    val sku: String,
    val name: String,
    val inventory: InventoryModel
)
