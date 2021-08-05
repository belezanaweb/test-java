package com.example.testkotlin.repository

import com.example.testkotlin.model.Inventory
import com.example.testkotlin.model.Product
import com.example.testkotlin.model.WareHouseType
import com.example.testkotlin.model.Warehouse
import org.springframework.stereotype.Repository

@Repository
class ProductRepositoryImpl:ProductRepository {

    companion object {
        val products = mutableListOf<Product>()
    }

    override fun findAll(): List<Product> = products

    override fun findBySku(sku: Int): Product? = products.find { it.sku == sku }

    override fun save(p: Product): Boolean = products.add(p)

    override fun update(p: Product): Boolean {
        var product = findBySku(p.sku)

        if (product != null) {
            product.apply {
                this.name = p.name
                this.inventory.warehouses.map { warehouse -> Warehouse(
                        warehouse.locality,
                        warehouse.quantity,
                        warehouse.type) }
            }
            return true
        }
        return false
    }

    override fun delete(sku: Int): Boolean = products.removeIf { it.sku == sku }

}