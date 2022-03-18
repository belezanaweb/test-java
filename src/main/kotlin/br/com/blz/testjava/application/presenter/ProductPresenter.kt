package br.com.blz.testjava.application.presenter

import br.com.blz.testjava.application.model.ProductModel
import br.com.blz.testjava.application.view.ProductView
import org.springframework.beans.factory.annotation.Autowired

class ProductPresenter @Autowired constructor(
    val presenter: InventoryPresenter
): AbstractPresenter<ProductModel, ProductView>() {

  override fun present(entity: ProductModel) = ProductView(
      sku = entity.sku,
      name = entity.name,
      inventory = presenter.present(entity.inventory)
  )

}
