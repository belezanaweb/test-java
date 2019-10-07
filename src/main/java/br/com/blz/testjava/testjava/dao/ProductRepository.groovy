package br.com.blz.testjava.testjava.dao

import br.com.blz.testjava.testjava.model.Product
import org.springframework.data.mongodb.repository.MongoRepository

interface ProductRepository extends MongoRepository<Product, Long> {
}
