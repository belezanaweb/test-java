package br.com.blz.testjava.application.factory

import br.com.blz.testjava.application.model.WarehouseModel
import br.com.blz.testjava.domain.entity.Warehouse
import org.springframework.stereotype.Component

@Component
class WarehousesFactory: AbstractFactory<WarehouseModel, Warehouse>() {

    override fun create(model: WarehouseModel) = Warehouse(
        locality = model.locality,
        quantity = model.quantity,
        type = model.type
    )

}
