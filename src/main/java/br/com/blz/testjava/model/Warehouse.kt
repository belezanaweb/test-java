package br.com.blz.testjava.model

import br.com.blz.testjava.enums.TypeWarehouse
import javax.persistence.EnumType
import javax.persistence.Enumerated

data class Warehouse (

  val locality: String,

  val quantity: Int,

  @field:Enumerated(EnumType.STRING)
  val type: TypeWarehouse
)
