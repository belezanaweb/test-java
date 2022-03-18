package br.com.blz.testjava.domain.repository

import br.com.blz.testjava.domain.entity.Product
import br.com.blz.testjava.domain.exception.ProductAlreadyExistsException
import br.com.blz.testjava.domain.exception.ProductNoFoundException
import org.springframework.stereotype.Component

@Component
object ProductRepository {

    private var products = mutableListOf<Product>()

    fun save(product: Product ): Product {
        products.forEach { run { if (it.equals(product)) throw ProductAlreadyExistsException(product.sku) } }
        products.add(product)
        return product
    }

    fun getAll(): MutableList<Product> {
        return products
    }

    fun get(sku: Int): Product {
        val product = products.firstOrNull { it.sku == sku }
        product ?: throw ProductNoFoundException(sku)
        return product
    }

    fun update(sku: Int, model: Product): Product {
        val product = this.get(sku)
        this.delete(product.sku)
        return this.save(model)
    }

    fun delete(sku: Int): Product {
        val product = this.get(sku)
        products.remove(product)
        return product
    }

}
