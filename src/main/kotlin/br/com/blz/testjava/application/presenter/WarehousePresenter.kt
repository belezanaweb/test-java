package br.com.blz.testjava.application.presenter

import br.com.blz.testjava.application.model.WarehouseModel
import br.com.blz.testjava.application.view.WarehouseView

class WarehousePresenter: AbstractPresenter<WarehouseModel, WarehouseView>() {

  override fun present(entity: WarehouseModel) = WarehouseView(
      locality = entity.locality,
      quantity = entity.quantity,
      type = entity.type
  )

}
