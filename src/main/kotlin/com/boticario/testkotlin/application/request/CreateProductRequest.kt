package com.boticario.testkotlin.application.request

data class CreateProductRequest(
        val sku: Long,
        val name: String,
        val inventory: InventoryRequest = InventoryRequest()
)
