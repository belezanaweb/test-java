package br.com.blz.testjava.application.service

import br.com.blz.testjava.application.web.controller.request.CreateProductRequest
import br.com.blz.testjava.application.web.controller.request.ProductResponse
import br.com.blz.testjava.domain.usecase.CreateProductUseCase
import org.springframework.stereotype.Service

@Service
class CreateProductService(
  private val createProductUseCase: CreateProductUseCase
) {

  fun createProduct(createProductRequest: CreateProductRequest): ProductResponse {
     return createProductUseCase.execute(createProductRequest.toDomain())
  }
}
