package br.com.blz.testjava.application.factory

import br.com.blz.testjava.application.model.ProductModel
import br.com.blz.testjava.domain.entity.Product
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProductFactory @Autowired constructor(
    private val factory: InventoryFactory
): AbstractFactory<ProductModel, Product>() {

  override fun create(model: ProductModel) = Product(
      sku = model.sku,
      name = model.name,
      inventory = factory.create(model.inventory)
  )

}
