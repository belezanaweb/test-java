package com.boticario.testkotlin.infrastructure.persistence.entity

import com.boticario.testkotlin.domain.entity.Inventory
import com.boticario.testkotlin.domain.entity.Product
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class ProductEntity(
        @Id
        private val sku: Long = 0L,
        private val name: String = "",

        @OneToMany(cascade = [CascadeType.ALL])
        private val warehouses: MutableList<WarehouseEntity> = mutableListOf()
) {
        fun toDomain() = Product(sku, name, Inventory(warehouses.map { it.toDomain() }.toMutableList()))


        companion object {
                fun fromDomain(product: Product) = ProductEntity(
                        product.sku,
                        product.name,
                        product.inventory.warehouses.map { WarehouseEntity.fromDomain(it) }.toMutableList()
                )
        }
}