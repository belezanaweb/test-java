package br.com.blz.testjava.model

import br.com.blz.testjava.enum.TypeWarehouse

data class Warehouse(
  val locality: String,
  val quantity: Int,
  val type: TypeWarehouse
)
