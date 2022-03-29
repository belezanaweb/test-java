package br.com.blz.testjava.warehouse.dto

import br.com.blz.testjava.warehouse.model.WarehouseTypeEnum

class WarehouseDTO(
  val locality: String,
  val quantity: Long,
  val type: WarehouseTypeEnum
)
