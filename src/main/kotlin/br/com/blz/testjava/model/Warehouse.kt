package br.com.blz.testjava.model

import br.com.blz.testjava.shared.enum.WarehouseType
import lombok.EqualsAndHashCode


@EqualsAndHashCode(onlyExplicitlyIncluded = true)
data class Warehouse(

  var locality: String = "",
  var quantity: Int = 0,
  val type: WarehouseType = WarehouseType.ECOMMERCE,
)
