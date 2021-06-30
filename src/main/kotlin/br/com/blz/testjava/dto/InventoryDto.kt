package br.com.blz.testjava.dto

import com.fasterxml.jackson.annotation.JsonProperty

class InventoryDto(

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    var quantity: Int = 0,

  @JsonProperty("warehouses")
    var warehouses: MutableList<WareHouseDto>? = null,
)
