package br.com.blz.testjava.model

class Warehouses {

    var locality: String? = null
    var quantity: Int = 0
    var type: String? = null

    constructor() : super() {
        // TODO Auto-generated constructor stub
    }

    constructor(locality: String, quantity: Int, type: String) : super() {
        this.locality = locality
        this.quantity = quantity
        this.type = type
    }

}
