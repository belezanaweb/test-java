package br.com.blz.testjava.product.mapper

import br.com.blz.testjava.inventory.mapper.InventoryMapper
import br.com.blz.testjava.product.dto.ProductInDTO
import br.com.blz.testjava.product.dto.ProductOutDTO
import br.com.blz.testjava.product.model.Product

class ProductMapper {
  companion object {

    fun toEntity(productInDTO: ProductInDTO): Product {
      return Product(
        productInDTO.sku,
        productInDTO.name,
        InventoryMapper.toEntity(productInDTO.inventory)
      )
    }

    fun toOutDto(product: Product): ProductOutDTO {
      return ProductOutDTO(
        product.sku,
        product.name,
        InventoryMapper.toOutDto(product.inventory),
        product.isMarketable
      )
    }
  }
}
