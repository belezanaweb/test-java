package br.com.blz.testjava.controller.request

import br.com.blz.testjava.validator.SkuAvailable
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Positive

data class ProductRequest(

  @field:Positive(message = "sku is required")
  @field:SkuAvailable(message = "sku already exists")
  val sku: Long,

  @field:NotBlank(message = "name is required")
  val name: String,

  @field:Valid
  val inventory: InventoryRequest
)
