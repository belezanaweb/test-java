package br.com.blz.testjava.application.factory

import br.com.blz.testjava.application.model.InventoryModel
import br.com.blz.testjava.domain.entity.Inventory
import br.com.blz.testjava.domain.entity.Warehouse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class InventoryFactory @Autowired constructor(
    private val factory: WarehousesFactory
) : AbstractFactory<InventoryModel, Inventory>() {

    override fun create(model: InventoryModel) = Inventory(
        warehouses = model.warehouses.map { factory.create(it) } as MutableList<Warehouse>
    )

}
