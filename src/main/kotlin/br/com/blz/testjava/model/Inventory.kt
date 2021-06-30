package br.com.blz.testjava.model

import lombok.EqualsAndHashCode

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
data class Inventory(

    @EqualsAndHashCode.Include
    val id: Long? = null,
    var quantity: Int = 0,
    var warehouses: MutableList<Warehouse>? = null
)
