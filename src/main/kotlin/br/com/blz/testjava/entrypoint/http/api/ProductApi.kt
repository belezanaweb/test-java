package br.com.blz.testjava.entrypoint.http.api

import br.com.blz.testjava.application.factory.ProductFactory
import br.com.blz.testjava.application.model.ProductModel
import br.com.blz.testjava.application.presenter.ProductPresenter
import br.com.blz.testjava.application.service.ProductService
import br.com.blz.testjava.application.view.ProductView
import br.com.blz.testjava.domain.entity.Product
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductApi @Autowired constructor(
    private val service: ProductService,
    private val factory: ProductFactory,
    private val presenter: ProductPresenter
) {

    @PostMapping("/products")
    fun crete(@RequestBody model: ProductModel): ProductView {
        val domain = service.save(factory.create(model))
        return presenter.present(domain)
    }

    @GetMapping("/products")
    fun getAll(): MutableList<Product> {
        return service.getAll()
    }

}
