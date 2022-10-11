package br.com.blz.testjava.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Inventory(
  @JsonProperty("warehouses") val warehouses: List<Warehouse>
) {
  fun calculateQuantity(): Int {
    return warehouses.sumOf { it.quantity }
  }

  var quantity: Int? = null
}
