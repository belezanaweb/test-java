package br.com.blz.testjava.domain.usecase

import br.com.blz.testjava.application.web.controller.request.ProductResponse
import br.com.blz.testjava.domain.entity.CreateProduct
import br.com.blz.testjava.domain.exception.AlreadyExistsException
import br.com.blz.testjava.domain.repository.ProductRepository
import br.com.blz.testjava.domain.repository.WarehouseRepository
import org.springframework.stereotype.Component

@Component
class CreateProductUseCase (
  private val productRepository: ProductRepository,
  private val warehouseRepository: WarehouseRepository
): UseCase<CreateProduct, ProductResponse> {
  override fun execute(input: CreateProduct): ProductResponse {
    if (productRepository.existsById(input.sku)) {
      throw AlreadyExistsException("SKU ${input.sku} already exists.")
    }
    val savedProduct = productRepository.save(input.toProduct())
    val savedWarehouses = input.inventory.map {
      warehouseRepository.save(it, input.sku)
    }
    return ProductResponse.fromDomain(savedProduct, savedWarehouses)
  }
}
