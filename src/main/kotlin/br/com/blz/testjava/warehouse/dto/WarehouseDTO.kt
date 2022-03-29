package br.com.blz.testjava.warehouse.dto

import br.com.blz.testjava.warehouse.model.WarehouseTypeEnum

data class WarehouseDTO(
  val locality: String,
  val quantity: Int,
  val type: WarehouseTypeEnum
)
