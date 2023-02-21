package com.boticario.testkotlin.application.service

import com.boticario.testkotlin.application.request.CreateProductRequest
import com.boticario.testkotlin.application.request.UpdateProductRequest
import com.boticario.testkotlin.domain.entity.Product
import org.springframework.stereotype.Service

@Service
interface ProductService {
    fun insertProductInStock(request: CreateProductRequest): Product

    fun updateProductFromStock(request: UpdateProductRequest, sku: Long): Product

    fun getProductFromStock(sku: Long): Product

    fun deleteProductFromStock(sku: Long)
}