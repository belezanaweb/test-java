package com.example.testkotlin.controller

import com.example.testkotlin.exception.ProductAlreadyExistsException
import com.example.testkotlin.model.Product
import com.example.testkotlin.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("products")
class ProductController (val productService: ProductService){

    @GetMapping
    fun findAll(): ResponseEntity<List<Product>> {

        val products = productService.findAll()
        if (products.isNotEmpty()) {
            return ResponseEntity<List<Product>>(products, HttpStatus.OK)
        }

        return ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT)

    }

    @GetMapping("/{sku}")
    fun findBySku(@PathVariable sku: Int): ResponseEntity<Product> {

        val product = productService.findBySku(sku)
        if (product != null) {
            return ResponseEntity<Product>(product, HttpStatus.OK)
        }

        return ResponseEntity<Product>(HttpStatus.NOT_FOUND)
    }

    @PostMapping
    fun post(@RequestBody product: Product): ResponseEntity<Product> {

        if (productService.findBySku(product.sku) != null) {
            throw ProductAlreadyExistsException("Product ${product.sku} already exist")
        }
        productService.save(product)
        return ResponseEntity<Product>(product, HttpStatus.CREATED)

    }

    @PutMapping
    fun put(@RequestBody product: Product): ResponseEntity<Product> {

        if (productService.update(product)) {
            return ResponseEntity<Product>(product, HttpStatus.OK)
        }
        return ResponseEntity<Product>(HttpStatus.NOT_FOUND)

    }

    @DeleteMapping("/{sku}")
    fun delete(@PathVariable sku: Int): ResponseEntity<Boolean> {

        if (productService.delete(sku)) {
            return ResponseEntity<Boolean>(true, HttpStatus.OK)
        }
        return ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND)

    }
}
