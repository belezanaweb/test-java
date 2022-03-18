package br.com.blz.testjava.application.presenter

import br.com.blz.testjava.application.view.WarehouseView
import br.com.blz.testjava.domain.entity.Warehouse
import org.springframework.stereotype.Component

@Component
class WarehousePresenter: AbstractPresenter<Warehouse, WarehouseView>() {

  override fun present(entity: Warehouse) = WarehouseView(
      locality = entity.locality,
      quantity = entity.quantity,
      type = entity.type
  )

}
