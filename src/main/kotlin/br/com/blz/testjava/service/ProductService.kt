package br.com.blz.testjava.service

import br.com.blz.testjava.domain.Product
import br.com.blz.testjava.repository.ProductRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ProductService(
	val repository: ProductRepository

) {

	fun list(): Flux<Product> = repository.list()

	fun findBySku(sku: String): Mono<Product> = repository.findBySku(sku)

	fun create(product: Product): Mono<Void> = repository.insert(product)

	fun update(sku: String, product: Product) = repository.save(sku, product)

	fun delete(sku: String): Mono<Void> = repository.delete(sku)
}