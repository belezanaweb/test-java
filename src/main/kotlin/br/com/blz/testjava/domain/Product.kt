package br.com.blz.testjava.domain

data class Product(
    val sku: String,
    val name: String,
    val inventory: Inventory,
    val isMarketable: Boolean
)
