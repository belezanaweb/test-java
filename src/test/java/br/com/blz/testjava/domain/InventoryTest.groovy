package br.com.blz.testjava.domain

import spock.lang.Specification

import static br.com.blz.testjava.domain.WarehouseType.ECOMMERCE
import static br.com.blz.testjava.domain.WarehouseType.PHYSICAL_STORE

class InventoryTest extends Specification {

    void 'Given an inventory with warehouses then sum all warehouse quantity' () {
        given:
            Inventory inventory = new Inventory(warehouses: [
                new Warehouse(locality: "SP", quantity: 10, type: ECOMMERCE),
                new Warehouse(locality: "TO", quantity: 10, type: ECOMMERCE),
                new Warehouse(locality: "MOEMA", quantity: 10, type: PHYSICAL_STORE)
            ])

        when:
            def quantity = inventory.getQuantity()

        then:
            quantity == 30
    }
}