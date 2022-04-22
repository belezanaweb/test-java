package br.com.blz.testjava.controller.request

import br.com.blz.testjava.enums.TypeWarehouse
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import javax.validation.constraints.PositiveOrZero

data class WarehouseRequest (
  @field:NotBlank(message =  "locality is required")
  val locality: String,

  @field:PositiveOrZero(message =  "quantity is required")
  val quantity: Int,

  @field:Enumerated(EnumType.STRING)
  val type: TypeWarehouse
)
