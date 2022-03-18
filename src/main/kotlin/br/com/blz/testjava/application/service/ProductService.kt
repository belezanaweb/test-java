package br.com.blz.testjava.application.service

import br.com.blz.testjava.domain.entity.Product
import br.com.blz.testjava.domain.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService @Autowired constructor(
    private val repository: ProductRepository
){

    fun save(product: Product): Product {
        return repository.save(product)
    }

    fun getAll(): MutableList<Product> {
        return repository.getAll()
    }

    fun get(sku: Int): Product {
        return repository.get(sku)
    }

}
