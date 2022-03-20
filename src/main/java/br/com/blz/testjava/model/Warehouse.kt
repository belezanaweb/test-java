package br.com.blz.testjava.model

import br.com.blz.testjava.enum.ProductType

class Warehouse(
    var locality:String,
    var quantity:Int,
    var type:ProductType
) {
}
