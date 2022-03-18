package br.com.blz.testjava.application.presenter

import br.com.blz.testjava.application.view.ProductView
import br.com.blz.testjava.domain.entity.Product
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProductPresenter @Autowired constructor(
    val presenter: InventoryPresenter
): AbstractPresenter<Product, ProductView>() {

  override fun present(entity: Product) = ProductView(
      sku = entity.sku,
      name = entity.name,
      inventory = presenter.present(entity.inventory),
      isMarketable = true
  )

}
