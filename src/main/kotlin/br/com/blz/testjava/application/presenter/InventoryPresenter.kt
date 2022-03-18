package br.com.blz.testjava.application.presenter

import br.com.blz.testjava.application.model.InventoryModel
import br.com.blz.testjava.application.view.InventoryView
import org.springframework.beans.factory.annotation.Autowired

class InventoryPresenter @Autowired constructor(
    val presenter: WarehousePresenter
): AbstractPresenter<InventoryModel, InventoryView>() {

  override fun present(entity: InventoryModel) = InventoryView(
      quantity = entity.quantity,
      warehouses = presenter.present(entity.warehouses)
  )

}
