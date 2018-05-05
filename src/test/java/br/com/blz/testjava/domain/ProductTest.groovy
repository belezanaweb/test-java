package br.com.blz.testjava.domain

import spock.lang.Specification

import static br.com.blz.testjava.domain.WarehouseType.ECOMMERCE

class ProductTest extends Specification {

    void 'Given a product with quantity greater than zero then isMarketable'() {
        given:
            Product product = new Product(inventory: new Inventory(warehouses: [
                new Warehouse(locality: 'SP', quantity: 10, type: ECOMMERCE),
                new Warehouse(locality: 'TO', quantity: 5, type: ECOMMERCE)
            ]))

        when:
            def marketable = product.marketable

        then:
            marketable
    }

    void 'Given a product with quantity zero then isMarketable false'() {
        given:
            Product product = new Product(inventory: new Inventory(warehouses: [
                new Warehouse(locality: 'SP', quantity: 0, type: ECOMMERCE),
                new Warehouse(locality: 'TO', quantity: 0, type: ECOMMERCE)
            ]))

        when:
            def marketable = product.marketable

        then:
            !marketable
    }

}
