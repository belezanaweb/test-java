package br.com.blz.testjava.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Product(
  @JsonProperty("sku") val sku: Long = 0,
  @JsonProperty("name") val name: String = "",
  @JsonProperty("inventory") val inventory: Inventory
) {
  @JsonProperty("isMarketable")
  var marketable: Boolean? = null

  fun isMarketable(): Boolean {
    inventory.quantity?.let { return it > 0 } ?: return false
  }
}
