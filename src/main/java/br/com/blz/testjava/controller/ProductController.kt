package br.com.blz.testjava.controller

import br.com.blz.testjava.exception.ProductExistingException
import br.com.blz.testjava.exception.ProductNotFoundException
import br.com.blz.testjava.model.Product
import br.com.blz.testjava.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("product")
class ProductController {

    @Autowired lateinit var productService: ProductService

    @GetMapping
    fun findAll(): MutableList<Product> {
        return productService.findAll().toMutableList()
    }

    @GetMapping("/{sku}")
    fun findById(@PathVariable sku: Long, @RequestBody product: Product): ResponseEntity<Product> {
        return try {
            val foundProduct = productService.findById(sku)
                if (foundProduct != null)
                 productService.findProductById(sku, product)

                ResponseEntity.ok().body(product)
        } catch(e: ProductNotFoundException) {
                ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun save(@RequestBody product: Product): ResponseEntity<Product> {
        try {
            productService.save(product)
            return ResponseEntity.status(HttpStatus.CREATED).body(product)

        } catch (e: ProductExistingException) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    @PutMapping("/{sku}")
    fun update(@RequestBody product: Product, @PathVariable sku: Long) : ResponseEntity<Product> {

        return try {
            productService.update(product)
            ResponseEntity.status(HttpStatus.OK).body(product)
        } catch (e: ProductNotFoundException) {
            ResponseEntity.notFound().build()
        } catch (e: Exception) {
            return ResponseEntity(product, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/{sku}")
    fun delete(@PathVariable sku: Long) : ResponseEntity<Void> {
        try {
            productService.delete(sku)
            return ResponseEntity<Void>(HttpStatus.OK)
        } catch (e: ProductNotFoundException) {
            return return ResponseEntity.notFound().build();
        } catch (e: Exception) {
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}
