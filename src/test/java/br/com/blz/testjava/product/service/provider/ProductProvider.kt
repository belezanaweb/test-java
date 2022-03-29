package br.com.blz.testjava.product.service.provider

import br.com.blz.testjava.inventory.model.Inventory
import br.com.blz.testjava.inventory.provider.InventoryProvider
import br.com.blz.testjava.product.dto.ProductInDTO
import br.com.blz.testjava.product.model.Product

class ProductProvider {
  companion object {
    fun createInDto(): ProductInDTO {
      return ProductInDTO(
        43264L,
        "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g",
        InventoryProvider.createInDto()
      )
    }

    fun createEntity(): Product {
      return Product(
        43264L,
        "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g",
        InventoryProvider.createEntityWithATotalOf15WareHouse()
      )
    }

    fun createEntity(inventory: Inventory): Product {
      return Product(
        43264L,
        "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g",
        inventory
      )
    }
  }
}
