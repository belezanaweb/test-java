package br.com.blz.testjava.model

class Inventory {

    var quantity: Int? = null
    lateinit var warehouses: List<Warehouses>

    constructor() : super() {}

    constructor(quantity: Int, warehouses: List<Warehouses>) : super() {
        this.quantity = quantity
        this.warehouses = warehouses
    }

}
