package br.com.blz.testjava.adapters.controllers

import br.com.blz.testjava.domain.model.Product
import br.com.blz.testjava.domain.usecases.ProductUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/product")
class ProductController(private val productUseCase: ProductUseCase) {

    @PostMapping
    fun create(@RequestBody product: Product) : ResponseEntity<HttpStatus> {
       productUseCase.create(product)
       return status(CREATED).build()
    }

    @DeleteMapping("/{sku}")
    fun delete(@PathVariable sku: Long) = productUseCase.delete(sku)

    @GetMapping(value = ["", "/{sku}"])
    fun read(@PathVariable(required = false) sku: Optional<Long>) = productUseCase.read(sku)

    @PutMapping("/{sku}")
    fun update(@PathVariable sku: Long, @RequestBody product: Product) = productUseCase.update(sku, product)

}