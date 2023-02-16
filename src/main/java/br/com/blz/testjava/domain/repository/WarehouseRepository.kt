package br.com.blz.testjava.domain.repository

import br.com.blz.testjava.domain.entity.Warehouse

interface WarehouseRepository {
  fun save(warehouse: Warehouse, sku: Int): Warehouse

  fun findBySku(sku: Int): List<Warehouse>

  fun deleteBySku(sku: Int)
}
