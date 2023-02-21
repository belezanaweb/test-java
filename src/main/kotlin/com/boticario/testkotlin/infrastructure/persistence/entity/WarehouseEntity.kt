package com.boticario.testkotlin.infrastructure.persistence.entity

import com.boticario.testkotlin.domain.entity.Warehouse
import com.boticario.testkotlin.domain.enum.WarehouseType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class WarehouseEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private val id: Int = 0,
        private val locality: String = "",
        private val quantity: Int = 0,
        private val type: String = "",
        @ManyToOne
        @JoinColumn(name = "product_sku")
        private val product: ProductEntity? = null
) {
        fun toDomain() = Warehouse(
                locality,
                quantity,
                WarehouseType.valueOf(type)
        )

        companion object {
                fun fromDomain(warehouse: Warehouse) = WarehouseEntity(
                        locality = warehouse.locality,
                        quantity = warehouse.quantity,
                        type = warehouse.type.name
                )
        }
}