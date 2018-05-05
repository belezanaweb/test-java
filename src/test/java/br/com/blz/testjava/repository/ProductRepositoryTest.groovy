package br.com.blz.testjava.repository

import br.com.blz.testjava.domain.Product
import spock.lang.Specification

class ProductRepositoryTest extends Specification {

    private ProductRepository repository

    void setup() {
        repository = new ProductRepository()
    }

    void cleanup() {
        repository.cleanUp()
    }

    void 'Given a product saved then find by id' () {
        given:
            Product product = new Product(sku: 123)

        when:
            repository.save(product)

        then:
            repository.findBySku(product.sku).isPresent()
    }

    void 'Given a product saved then update product name' () {
        given:
            String oldName = "oldName"
            String newName = "newName"
            Product product = new Product(sku: 123, name: oldName)

        when:
            repository.save(product)
            product.setName(newName)
            repository.update(123, product)

        then:
            repository.findBySku(product.sku).get().name == newName
    }

    void 'Given a product saved then remove from list' () {
        given:
            Product product = new Product(sku: 123)

        when:
            repository.save(product)

        then:
            assert repository.findBySku(product.sku).isPresent()
            assert repository.remove(product.sku)
    }
}
