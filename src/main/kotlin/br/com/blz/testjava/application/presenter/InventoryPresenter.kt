package br.com.blz.testjava.application.presenter

import br.com.blz.testjava.application.view.InventoryView
import br.com.blz.testjava.domain.entity.Inventory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class InventoryPresenter @Autowired constructor(
    val presenter: WarehousePresenter
): AbstractPresenter<Inventory, InventoryView>() {

  override fun present(entity: Inventory) = InventoryView(
      quantity = 1,
      warehouses = entity.warehouses.map { presenter.present(it) }
  )

}
