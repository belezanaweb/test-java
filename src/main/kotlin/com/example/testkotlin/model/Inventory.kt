package com.example.testkotlin.model

data class Inventory (
        var quantity:Int,
        val warehouses:List<Warehouse>
)