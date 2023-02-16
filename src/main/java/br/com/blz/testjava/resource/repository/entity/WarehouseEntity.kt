package br.com.blz.testjava.resource.repository.entity

import br.com.blz.testjava.domain.entity.Warehouse
import br.com.blz.testjava.domain.entity.WarehouseType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class WarehouseEntity(
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  val id: Int = 0,

  val locality: String = "",
  val quantity: Int = 0,
  val type: String = "",
  val sku: Int = 0
) {
  fun toDomain() = Warehouse(
    locality = locality,
    quantity = quantity,
    type = WarehouseType.valueOf(type)
  )
  companion object {
    fun fromDomain(warehouse: Warehouse, sku: Int = 0) = WarehouseEntity(
      locality = warehouse.locality,
      quantity = warehouse.quantity,
      type = warehouse.type.name,
      sku = sku
    )
  }
}
