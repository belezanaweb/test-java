package br.com.blz.testjava.domain.usecase

import br.com.blz.testjava.application.web.controller.request.ProductResponse
import br.com.blz.testjava.domain.entity.UpdateProduct
import br.com.blz.testjava.domain.exception.NotFoundException
import br.com.blz.testjava.domain.repository.ProductRepository
import br.com.blz.testjava.domain.repository.WarehouseRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
open class UpdateProductUseCase (
  private val productRepository: ProductRepository,
  private val warehouseRepository: WarehouseRepository
): UseCase<UpdateProduct, ProductResponse> {

  @Transactional
  override fun execute(input: UpdateProduct): ProductResponse {
    productRepository.findById(input.sku.toString())?.let {
      val savedProduct = productRepository.save(input.toProduct())

      warehouseRepository.deleteBySku(it.sku)
      val savedWarehouses = input.inventory.map { warehouse ->
        warehouseRepository.save(warehouse, input.sku)
      }

      return ProductResponse.fromDomain(savedProduct, savedWarehouses)
    }
    throw NotFoundException("Not found product for SKU ${input.sku}")
  }
}
