package br.com.blz.testjava.model

data class Warehouse(
  val locality: String,
  val quantity: Long,
  val type: String // TODO: poderia ser um enum
)
