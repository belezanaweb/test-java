package com.boticario.testkotlin.domain.repository

import com.boticario.testkotlin.domain.entity.Product

interface ProductRepository {
    fun save(product: Product): Product

    fun findOne(sku: Long): Product?

    fun deleteOne(sku: Long)
}
