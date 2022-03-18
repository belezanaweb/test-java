package br.com.blz.testjava.entrypoint.http.api

import br.com.blz.testjava.application.factory.ProductFactory
import br.com.blz.testjava.application.model.ProductModel
import br.com.blz.testjava.application.presenter.ProductPresenter
import br.com.blz.testjava.application.service.ProductService
import br.com.blz.testjava.application.view.ProductView
import br.com.blz.testjava.domain.entity.Product
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class ProductApi @Autowired constructor(
    private val service: ProductService,
    private val factory: ProductFactory,
    private val presenter: ProductPresenter
) {

    @PostMapping("/products")
    fun crete(@RequestBody model: ProductModel): ResponseEntity<ProductView> {
        val domain = service.save(factory.create(model))
        return ResponseEntity<ProductView>(presenter.present(domain), HttpStatus.CREATED)
    }

    @GetMapping("/products/{sku}")
    fun get(@PathVariable sku: Int): ResponseEntity<ProductView> {
        val domain = service.get(sku)
        return ResponseEntity<ProductView>(presenter.present(domain), HttpStatus.OK)
    }

    @PutMapping("/products/{sku}")
    fun update(@PathVariable sku: Int, @RequestBody model: ProductModel): ResponseEntity<ProductView> {
        val domain = service.update(sku, factory.create(model))
        return ResponseEntity<ProductView>(presenter.present(domain), HttpStatus.OK)
    }

    @DeleteMapping("/products/{sku}")
    fun delete(@PathVariable sku: Int): ResponseEntity<ProductView> {
        val domain = service.delete(sku)
        return ResponseEntity<ProductView>(presenter.present(domain), HttpStatus.OK)
    }

    @GetMapping("/products")
    fun getAll(): MutableList<Product> {
        return service.getAll()
    }

}
