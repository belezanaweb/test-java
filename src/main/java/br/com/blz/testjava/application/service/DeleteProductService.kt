package br.com.blz.testjava.application.service

import br.com.blz.testjava.domain.usecase.DeleteProductUseCase
import org.springframework.stereotype.Service

@Service
class DeleteProductService(
  private val deleteProductUseCase: DeleteProductUseCase
) {

  fun deleteProduct(sku: String) {
    deleteProductUseCase.execute(sku)
  }
}
