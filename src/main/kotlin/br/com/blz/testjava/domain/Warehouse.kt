package br.com.blz.testjava.domain

import br.com.blz.testjava.domain.enums.WarehouseType

class Warehouse(
  val locality: String,
  val quantity: Int,
  val type: WarehouseType
)
