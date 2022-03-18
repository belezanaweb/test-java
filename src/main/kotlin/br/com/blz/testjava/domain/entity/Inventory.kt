package br.com.blz.testjava.domain.entity

class Inventory(
    private var _quantity: Int? = 0,
    val warehouses: MutableList<Warehouse>
)
