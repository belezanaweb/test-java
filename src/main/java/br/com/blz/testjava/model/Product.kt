package br.com.blz.testjava.model

class Product {

    var sku: Long = 0
    var name: String? = null
    var inventory: Inventory? = null
    var isMarketable: Boolean? = null

    constructor() : super() {}

    constructor(sku: Long, name: String, inventory: Inventory, isMarketable: Boolean) : super() {
        this.sku = sku
        this.name = name
        this.inventory = inventory
        this.isMarketable = isMarketable
    }

}
