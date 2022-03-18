package br.com.blz.testjava.application.model

import br.com.blz.testjava.domain.enums.Locality
import br.com.blz.testjava.domain.enums.WarehouseType

data class WarehouseModel(
  val locality: Locality,
  val quantity: Int,
  val type: WarehouseType
)
