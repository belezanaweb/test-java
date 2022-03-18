package br.com.blz.testjava.domain.entity

import br.com.blz.testjava.domain.entity.enums.WarehouseType

class Warehouse(
  val locality: String,
  val quantity: Int,
  val type: WarehouseType
)
