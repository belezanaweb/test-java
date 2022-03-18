package br.com.blz.testjava.application.view

data class ProductView(
    val sku: String,
    val name: String,
    val inventory: InventoryView
)
