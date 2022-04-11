package br.com.blz.testjava.model.responses

import br.com.blz.testjava.model.Warehouse

data class InventoryResponse(
  val quantity: Int,
  val warehouses: List<Warehouse>
)
