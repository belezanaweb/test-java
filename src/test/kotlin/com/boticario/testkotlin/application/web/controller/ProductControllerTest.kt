package com.boticario.testkotlin.application.web.controller

import com.boticario.testkotlin.application.request.CreateProductRequest
import com.boticario.testkotlin.application.request.UpdateProductRequest
import com.boticario.testkotlin.application.service.ProductService
import com.boticario.testkotlin.domain.entity.Product
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class ProductControllerTest {
    private val productService = mockk<ProductService>()
    private val createProductRequest = mockk<CreateProductRequest>(relaxed = true)
    private val updateProductRequest = mockk<UpdateProductRequest>(relaxed = true)
    private val sku = 1234L
    private val product = mockk<Product>(relaxed = true)
    private val productController = ProductController(productService)

    @Test
    fun `Given a product creation request, When valid parameters are input, Then insert product service must be invoked`() {
        every { productService.insertProductInStock(any()) } returns product
        productController.createProduct(createProductRequest)

        verify { productService.insertProductInStock(any()) }
    }

    @Test
    fun `Given a product consult request, When valid parameters are input, Then get product service must be invoked`() {
        every { productService.getProductFromStock(any()) } returns product
        productController.getProduct(sku)

        verify { productService.getProductFromStock(any()) }
    }

    @Test
    fun `Given a product change request, When valid parameters are input, Then update product service must be invoked`() {
        every { productService.updateProductFromStock(any(), any()) } returns product
        productController.updateProduct(updateProductRequest, sku)

        verify { productService.updateProductFromStock(any(), any()) }
    }

    @Test
    fun `Given a product delete request, When valid parameters are input, Then delete product service must be invoked`() {
        every { productService.deleteProductFromStock(any()) } just runs
        productController.deleteProduct(sku)

        verify { productService.deleteProductFromStock(any()) }
    }
}