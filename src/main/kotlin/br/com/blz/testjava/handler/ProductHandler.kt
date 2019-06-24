package br.com.blz.testjava.handler

import br.com.blz.testjava.domain.Product
import br.com.blz.testjava.service.ProductService
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class ProductHandler(
	val service: ProductService
) {

	fun list(request: ServerRequest): Mono<ServerResponse> {
		return ServerResponse
			.ok()
			.contentType(APPLICATION_JSON)
			.body(service.list(), Product::class.java)
	}

	fun findBySku(request: ServerRequest): Mono<ServerResponse> {
		return ServerResponse
			.ok()
			.contentType(APPLICATION_JSON)
			.body(service.findBySku(request.pathVariable("sku")), Product::class.java)
	}

	fun create(request: ServerRequest): Mono<ServerResponse> {
		return request
			.bodyToMono(Product::class.java)
			.flatMap { product ->
				ServerResponse
					.ok()
					.contentType(APPLICATION_JSON)
					.body(service.create(product), Void::class.java)
			}
	}

	fun update(request: ServerRequest): Mono<ServerResponse> {
		return request
			.bodyToMono(Product::class.java)
			.flatMap { product ->
				ServerResponse
					.ok()
					.contentType(APPLICATION_JSON)
					.body(service.update(request.pathVariable("sku"), product), Void::class.java)
			}
	}

	fun delete(request: ServerRequest): Mono<ServerResponse> {
		return ServerResponse
			.accepted()
			.contentType(APPLICATION_JSON)
			.body(service.delete(request.pathVariable("sku")), Void::class.java)
	}
}