package br.com.blz.testjava.domain

import br.com.blz.testjava.domain.enums.Locality
import br.com.blz.testjava.domain.enums.WarehouseType

class Warehouse(
  val anLocality: Locality,
  val quantity: Int,
  val type: WarehouseType
)
