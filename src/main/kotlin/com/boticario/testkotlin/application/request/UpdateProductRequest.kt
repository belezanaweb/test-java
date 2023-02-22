package com.boticario.testkotlin.application.request

data class UpdateProductRequest(
        val name: String? = null,
        val inventory: InventoryRequest? = null
)
