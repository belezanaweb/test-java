package com.boticario.testkotlin.application.service

import com.boticario.testkotlin.application.request.CreateProductRequest
import com.boticario.testkotlin.application.request.UpdateProductRequest
import com.boticario.testkotlin.application.response.ProductResponse
import com.boticario.testkotlin.domain.entity.Product
import org.springframework.stereotype.Service

@Service
interface ProductService {
    fun insertProductInStock(request: CreateProductRequest): ProductResponse

    fun updateProductFromStock(request: UpdateProductRequest, sku: Long): ProductResponse

    fun getProductFromStock(sku: Long): ProductResponse

    fun deleteProductFromStock(sku: Long)
}
