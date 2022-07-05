package br.com.blz.testkotlin.entity

import br.com.blz.testkotlin.enum.TypeEnum

class WarehouseEntity(
  var locality: String,
  var quantity: Int,
  var type: TypeEnum
)
