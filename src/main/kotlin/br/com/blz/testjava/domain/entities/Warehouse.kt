package br.com.blz.testjava.domain.entities

data class Warehouse (
  var locality: String,
  var quantity: Int,
  var type: WarehouseType,
)
