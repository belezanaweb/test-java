package br.com.blz.testjava.rest.dto

import br.com.blz.testjava.model.Product

class ProductDto(val sku:Long,
                 val name:String,
                 val inventory: InventoryDto,
                 var isMarketable: Boolean
) {

  companion object {
    fun fromProductModel(product: Product): ProductDto {
      return ProductDto(
        product.sku,
        product.name,
        InventoryDto.fromInventoryModel(product.inventory),
        product.isMarketable()
      )
    }
  }

  fun toProductModel(): Product {
    return Product(
      sku,
      name,
      inventory.toInventoryModel()
    )
  }
}
