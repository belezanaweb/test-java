package br.com.blz.testjava.application.service

import br.com.blz.testjava.application.web.controller.request.ProductResponse
import br.com.blz.testjava.application.web.controller.request.UpdateProductRequest
import br.com.blz.testjava.domain.usecase.UpdateProductUseCase
import org.springframework.stereotype.Service

@Service
class UpdateProductService(
  private val updateProductUseCase: UpdateProductUseCase
) {

  fun updateProduct(sku: String, updateProductRequest: UpdateProductRequest): ProductResponse {
    return updateProductUseCase.execute(updateProductRequest.toDomain(sku.toInt()))
  }
}
