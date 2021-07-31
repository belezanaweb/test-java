package br.com.blz.testjava.model

import br.com.blz.testjava.enum.TypeWarehouse
import com.fasterxml.jackson.annotation.JsonProperty

data class ProductResponse(
  val sku: Long,
  val name: String,
  val inventory: Inventory,
  @get:JsonProperty("isMarketable")
  var isMarketable: Boolean
)
