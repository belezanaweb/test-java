package br.com.blz.testjava.domain.usecase

import br.com.blz.testjava.domain.exception.NotFoundException
import br.com.blz.testjava.domain.repository.ProductRepository
import br.com.blz.testjava.domain.repository.WarehouseRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
open class DeleteProductUseCase (
  private val productRepository: ProductRepository,
  private val warehouseRepository: WarehouseRepository
): UseCase<String, Unit> {

  @Transactional
  override fun execute(input: String) {
    productRepository.findById(input)?.let {
      productRepository.deleteById(input.toInt())
      warehouseRepository.deleteBySku(input.toInt())
      return
    }
    throw NotFoundException("Not found product for SKU $input")
  }
}
