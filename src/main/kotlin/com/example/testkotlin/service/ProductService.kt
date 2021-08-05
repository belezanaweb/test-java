package com.example.testkotlin.service

import com.example.testkotlin.model.Product
import com.example.testkotlin.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(val productRepository: ProductRepository) {

    fun findAll(): List<Product> = productRepository.findAll()

    fun findBySku(sku: Int): Product? {
        var product = productRepository.findBySku(sku)
        if (product != null) {
            val sum = product.inventory.warehouses.map{ warehouse -> warehouse.quantity }.reduce { sum, quantity -> sum + quantity}
            product.inventory.quantity = sum
            if (product.inventory.quantity > 0) product.isMarkable = true
        }
        return product
    }

    fun save(p: Product) = productRepository.save(p)

    fun update(p: Product) = productRepository.update(p)

    fun delete(sku: Int): Boolean {
        if (findBySku(sku) != null) {
            productRepository.delete(sku)
            return true
        }
        return false
    }
}