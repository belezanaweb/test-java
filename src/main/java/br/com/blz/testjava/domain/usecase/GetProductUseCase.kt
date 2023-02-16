package br.com.blz.testjava.domain.usecase

import br.com.blz.testjava.application.web.controller.request.ProductResponse
import br.com.blz.testjava.domain.exception.NotFoundException
import br.com.blz.testjava.domain.repository.ProductRepository
import br.com.blz.testjava.domain.repository.WarehouseRepository
import org.springframework.stereotype.Component

@Component
class GetProductUseCase (
  private val productRepository: ProductRepository,
  private val warehouseRepository: WarehouseRepository
): UseCase<String, ProductResponse> {

  override fun execute(input: String): ProductResponse {
    productRepository.findById(input)?.let {
      val warehouses = warehouseRepository.findBySku(input.toInt())
      return ProductResponse.fromDomain(it, warehouses)
    }
    throw NotFoundException("Not found product for SKU $input")
  }
}
