package br.com.blz.testjava.application.model

data class InventoryModel(
    val quantity: Int,
    val warehouses: MutableList<WarehouseModel>
)
