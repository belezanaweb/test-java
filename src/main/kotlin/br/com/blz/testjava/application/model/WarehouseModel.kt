package br.com.blz.testjava.application.model

import br.com.blz.testjava.domain.entity.enums.WarehouseType

data class WarehouseModel(
  val locality: String,
  val quantity: Int,
  val type: WarehouseType
)
