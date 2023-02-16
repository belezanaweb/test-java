package br.com.blz.testjava.application.service

import br.com.blz.testjava.application.web.controller.request.ProductResponse
import br.com.blz.testjava.domain.usecase.GetProductUseCase
import org.springframework.stereotype.Service

@Service
class GetProductService(
  private val getProductUseCase: GetProductUseCase
) {

  fun getProduct(sku: String): ProductResponse {
    return getProductUseCase.execute(sku)
  }
}
