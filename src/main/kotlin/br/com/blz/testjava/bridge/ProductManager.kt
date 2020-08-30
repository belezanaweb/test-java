package br.com.blz.testjava.bridge

import br.com.blz.testjava.domain.model.Product
import java.util.*

interface ProductManager {
    fun deleteById(sku: Long)
    fun existsById(sku: Long): Boolean
    fun findAll(): Iterable<Product>
    fun findById(get: Long): Optional<Product>
    fun save(product: Product)
}