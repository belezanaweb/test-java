package br.com.blz.testjava.dto

import br.com.blz.testjava.shared.enum.WarehouseType
import com.fasterxml.jackson.annotation.JsonProperty

class WareHouseDto (

    @JsonProperty("locality")
    var locality: String = "",

    @JsonProperty("quantity")
    var quantity: Int = 0,

    @JsonProperty("type")
    var type: WarehouseType? = null

)
