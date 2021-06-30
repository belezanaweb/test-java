package br.com.blz.testjava.model

import lombok.EqualsAndHashCode

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
data class Product(
    @EqualsAndHashCode.Include
    var sku: Long? = null,
    var name: String? = null,
    var isMarketable: Boolean = false,
    var inventory: Inventory? = null
)
