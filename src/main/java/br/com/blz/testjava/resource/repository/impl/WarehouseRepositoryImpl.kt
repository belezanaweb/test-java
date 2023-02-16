package br.com.blz.testjava.resource.repository.impl

import br.com.blz.testjava.domain.entity.Warehouse
import br.com.blz.testjava.domain.repository.WarehouseRepository
import br.com.blz.testjava.resource.repository.entity.WarehouseEntity
import br.com.blz.testjava.resource.repository.jpa.JpaWarehouseRepository
import org.springframework.stereotype.Repository

@Repository
open class WarehouseRepositoryImpl (
  private val jpa: JpaWarehouseRepository
) : WarehouseRepository {
  override fun save(warehouse: Warehouse, sku: Int): Warehouse {
    return jpa.save(WarehouseEntity.fromDomain(warehouse, sku)).toDomain()
  }

  override fun findBySku(sku: Int): List<Warehouse> {
    return jpa.findBySku(sku).map { it.toDomain() }
  }

  override fun deleteBySku(sku: Int) {
    jpa.deleteBySku(sku)
  }

}
