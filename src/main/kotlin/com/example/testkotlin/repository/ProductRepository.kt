package com.example.testkotlin.repository

import com.example.testkotlin.model.Product

interface ProductRepository {

    fun findAll():List<Product>
    fun findBySku(sku:Int):Product?
    fun save(p:Product):Boolean
    fun update(p:Product):Boolean
    fun delete(sku:Int):Boolean

}