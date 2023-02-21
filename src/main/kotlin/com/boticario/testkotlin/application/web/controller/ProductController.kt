package com.boticario.testkotlin.application.web.controller

import com.boticario.testkotlin.application.request.CreateProductRequest
import com.boticario.testkotlin.application.request.UpdateProductRequest
import com.boticario.testkotlin.application.service.ProductService
import java.sql.SQLException
import org.springframework.http.HttpStatus
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/products")
class ProductController(private val productService: ProductService) {
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    fun createProduct(@RequestBody createProductRequest: CreateProductRequest) =
            productService.insertProductInStock(createProductRequest)

    @GetMapping("/{sku}")
    fun getProduct(@PathVariable sku: Long) = productService.getProductFromStock(sku)

    @PutMapping("/{sku}")
    @Transactional(rollbackFor = [SQLException::class])
    fun updateProduct(@RequestBody updateProductRequest: UpdateProductRequest, @PathVariable sku: Long) =
            productService.updateProductFromStock(updateProductRequest, sku)

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{sku}")
    fun deleteProduct(@PathVariable sku: Long) = productService.deleteProductFromStock(sku)

}
