package com.boticario.testkotlin.application.service.impl

import com.boticario.testkotlin.domain.repository.ProductRepository
import com.boticario.testkotlin.application.request.CreateProductRequest
import com.boticario.testkotlin.application.request.UpdateProductRequest
import com.boticario.testkotlin.application.response.ProductResponse
import com.boticario.testkotlin.application.service.ProductService
import com.boticario.testkotlin.domain.entity.Product
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ProductServiceImpl (
    private val productRepository: ProductRepository,
) : ProductService {
    override fun insertProductInStock(request: CreateProductRequest): ProductResponse {
        val alreadyExists = productRepository.findOne(request.sku)
        if (alreadyExists != null) throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Fail to CREATE product, sku already exists!"
        )

        val response = productRepository.save(Product(request.sku, request.name, request.inventory.toDomain()))

        return ProductResponse.fromDomain(response)
    }

    override fun updateProductFromStock(request: UpdateProductRequest, sku: Long): ProductResponse {
        val product = productRepository.findOne(sku) ?: throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Fail to UPDATE product, sku not found in database!"
        )

        val response = productRepository.save(
            Product(
              product.sku,
              request.name ?: product.name,
              request.inventory?.toDomain() ?: product.inventory
            )
        )

        return ProductResponse.fromDomain(response)

    }

    override fun getProductFromStock(sku: Long): ProductResponse {
      val response = productRepository.findOne(sku) ?: throw ResponseStatusException(
        HttpStatus.BAD_REQUEST,
        "Fail to FIND product, sku not found in database!"
      )

      return ProductResponse.fromDomain(response)
    }

    override fun deleteProductFromStock(sku: Long) =
            runCatching {
                productRepository.deleteOne(sku)
            }.onFailure {
                throw ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Fail to DELETE product, sku not found in database!"
                )
            }.getOrElse { }

}
