package com.boticario.testkotlin.application.repository

import com.boticario.testkotlin.domain.entity.Product

interface ProductRepository {
    fun save(product: Product): Product

    fun findOne(sku: Long): Product?

    fun deleteOne(sku: Long)
}