package br.com.blz.testjava.resource.repository.jpa

import br.com.blz.testjava.resource.repository.entity.WarehouseEntity
import org.springframework.data.repository.CrudRepository

interface JpaWarehouseRepository : CrudRepository<WarehouseEntity, Int> {

  fun findBySku(sku: Int): List<WarehouseEntity>

  fun deleteBySku(sku: Int)
}
