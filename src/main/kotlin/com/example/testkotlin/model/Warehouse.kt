package com.example.testkotlin.model

data class Warehouse(
        val locality: String,
        val quantity:Int,
        val type: WareHouseType
)

enum class WareHouseType {
    ECOMMERCE, PHYSICAL_STORE
}