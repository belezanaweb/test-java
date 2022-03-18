package br.com.blz.testjava.application.view

import br.com.blz.testjava.domain.enums.Locality
import br.com.blz.testjava.domain.enums.WarehouseType

data class WarehouseView(
    val locality: Locality,
    val quantity: Int,
    val type: WarehouseType
)
