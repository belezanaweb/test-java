package br.com.blz.testjava.service

import br.com.blz.testjava.domain.Product
import br.com.blz.testjava.exception.ConflictException
import br.com.blz.testjava.exception.NotFoundException
import br.com.blz.testjava.repository.ProductRepository
import spock.lang.Specification

class ProductServiceTest extends Specification {

    private ProductService service
    private ProductRepository repository

    void setup() {
        repository = new ProductRepository()
        service = new ProductService(repository)
    }

    void cleanup() {
        repository.cleanUp()
    }

    void 'Given a product then create product' () {
        given:
            Product product = new Product(sku: 123)

        when:
            service.create(product)

        then:
            repository.findBySku(product.getSku()).isPresent()
    }

    void 'Given a product already in list then throw ConflictException' () {
        given:
            Product product = new Product(sku: 123)
            repository.save(product)

        when:
            service.create(product)

        then:
            thrown(ConflictException)
    }

    void 'Given a sku not in list then throw not found exception' () {
        when:
            service.findBySku(123)

        then:
            thrown(NotFoundException)
    }

    void 'Given a product saved and removed then not found' () {
        given:
            Product product = new Product(sku: 123)
            repository.save(product)

        when:
            service.remove(product.getSku())

        then:
            !repository.findBySku(product.getSku()).isPresent()
    }
}
