package br.com.blz.testjava.domain.repository

import br.com.blz.testjava.domain.entity.Product
import br.com.blz.testjava.domain.exception.ProductAlreadyExistsException
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

}
