package br.com.blz.testjava.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Warehouse(
  @JsonProperty("locality") val locality: String = "",
  @JsonProperty("quantity") val quantity: Int = 0,
  @JsonProperty("type") val type: String = ""
)
