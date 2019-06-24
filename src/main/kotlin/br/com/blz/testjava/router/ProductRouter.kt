package br.com.blz.testjava.router

import br.com.blz.testjava.handler.ProductHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.router

@Configuration
class ProductRouter {

	@Bean
	internal fun product(handler: ProductHandler) = router {
		accept(APPLICATION_JSON).nest {
			"/products".nest {
				GET("/", handler::list)
				GET("/{sku}", handler::findBySku)
				POST("/", handler::create)
				PUT("/{sku}", handler::update)
				DELETE("/{sku}", handler::delete)
			}
		}
	}
}