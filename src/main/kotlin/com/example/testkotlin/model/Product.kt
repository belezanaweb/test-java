package com.example.testkotlin.model

data class Product(
        val sku:Int,
        var name: String,
        var inventory: Inventory,
        var isMarkable:Boolean,
)