package br.com.blz.testjava.repository

import br.com.blz.testjava.domain.Product
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class ProductRepository(
	private val products: MutableSet<Product> = mutableSetOf()

) {

	fun list(): Flux<Product> = Flux.fromIterable(products)

	fun findBySku(sku: String): Mono<Product> = Mono.justOrEmpty(products.find { it.sku == sku })

	fun insert(product: Product): Mono<Void> = Mono.just(products.add(product)).then()

	fun insert(list: Set<Product>): Mono<Void> = Mono.just(products.addAll(list)).then()

	fun save(sku: String, product: Product): Mono<Void> {
		return Mono.justOrEmpty(products.removeIf { it.sku == sku })
			.doOnNext { products.add(product) }.then()
	}

	fun delete(sku: String): Mono<Void> = Mono.justOrEmpty(products.removeIf { it.sku == sku }).then()
}