package com.boticario.testkotlin.application.service.impl

import com.boticario.testkotlin.domain.repository.ProductRepository

import com.boticario.testkotlin.application.request.WarehouseRequest
import com.boticario.testkotlin.application.response.InventoryResponse
import com.boticario.testkotlin.application.response.ProductResponse
import com.boticario.testkotlin.domain.enum.WarehouseType
import com.boticario.testkotlin.factory.ProductFactory.createProduct
import com.boticario.testkotlin.factory.ProductFactory.createProductRequest
import com.boticario.testkotlin.factory.ProductFactory.inventoryRequest
import com.boticario.testkotlin.factory.ProductFactory.updateProduct
import com.boticario.testkotlin.factory.ProductFactory.updateProductRequest
import io.mockk.called
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import io.mockk.verifyOrder
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

internal class ProductServiceImplTest {
    private val productRepository = mockk<ProductRepository>()
    private val productService = ProductServiceImpl(productRepository)

    @BeforeEach
    fun setUp() {
        every { productRepository.findOne(any()) } returns createProduct
    }

    @Test
    fun `Given a create product request, When valid parameters are input, Then insert product repository must be invoked and return the created product`() {
        every { productRepository.save(any()) } returns createProduct
        every { productRepository.findOne(any()) } returns null

        val result = productService.insertProductInStock(createProductRequest)

        verifyOrder {
            productRepository.findOne(createProduct.sku)
            productRepository.save(createProduct)
        }

        assert(result == ProductResponse.fromDomain(createProduct))
    }

    @Test
    fun `Given a create product request, When an invalid sku is input, Then must be thrown an exception in the find product step`() {
        val result = assertThrows<ResponseStatusException> { productService.insertProductInStock(createProductRequest) }

        verifyOrder {
            productRepository.findOne(createProduct.sku)
            productRepository.save(any()) wasNot called
        }

        assert(result.reason == "Fail to CREATE product, sku already exists!")
        assert(result.statusCode == HttpStatus.BAD_REQUEST)
    }

    @Test
    fun `Given an update product request, When only the name is input, Then must be updated only the name of the product`() {
        every { productRepository.save(any()) } returns updateProduct

        val result = productService.updateProductFromStock(updateProductRequest, createProduct.sku)

        verifyOrder {
            productRepository.findOne(createProduct.sku)
            productRepository.save(updateProduct)
        }

        assert(result.name == updateProduct.name)
        assert(result.inventory == InventoryResponse.fromDomain(createProduct.inventory))
    }

    @Test
    fun `Given an update product request, When a all fields are input, Then must be overwrite all fields`() {
        val overwriteInventoryRequest = inventoryRequest.copy(
                mutableListOf(
                        WarehouseRequest("MG", 42, WarehouseType.PHYSICAL_STORE),
                        WarehouseRequest("ES", 28, WarehouseType.ECOMMERCE)
                )
        )
        val updateAllFields = updateProduct.copy(inventory = overwriteInventoryRequest.toDomain())
        val updateAllFieldsRequest = updateProductRequest.copy(inventory = overwriteInventoryRequest)
        every { productRepository.save(any()) } returns updateAllFields
        setUp()

        val result = productService.updateProductFromStock(updateAllFieldsRequest, createProduct.sku)

        verifyOrder {
            productRepository.findOne(createProduct.sku)
            productRepository.save(updateAllFields)
        }

        assert(result.name == updateProduct.name)
        assert(result.inventory == InventoryResponse.fromDomain(updateAllFields.inventory))
    }

    @Test
    fun `Given an update product request, When an invalid sku is input, Then must be thrown an exception in the find product step`() {
        every { productRepository.findOne(any()) } returns null

        val result = assertThrows<ResponseStatusException> {
            productService.updateProductFromStock(
                updateProductRequest,
                createProduct.sku
            )
        }

        verifyOrder {
            productRepository.findOne(createProduct.sku)
            productRepository.save(any()) wasNot called
        }

        assert(result.reason == "Fail to UPDATE product, sku not found in database!")
        assert(result.statusCode == HttpStatus.BAD_REQUEST)
    }

    @Test
    fun `Given a search product request, When a valid sku is input, Then return found product`() {
        val result = productService.getProductFromStock(createProduct.sku)

        verify { productRepository.findOne(createProduct.sku) }
        assert(result == ProductResponse.fromDomain(createProduct))
    }

    @Test
    fun `Given a search product request, When an invalid sku is input, Then must be thrown an exception`() {
        every { productRepository.findOne(any()) } returns null

        val result = assertThrows<ResponseStatusException> { productService.getProductFromStock(createProduct.sku) }

        verify { productRepository.findOne(createProduct.sku) }
        assert(result.reason == "Fail to FIND product, sku not found in database!")
        assert(result.statusCode == HttpStatus.BAD_REQUEST)
    }

    @Test
    fun `Given a delete product request, When a valid sku is input, Then delete product repository must be invoke`() {
        every { productRepository.deleteOne(any()) } just runs

        productService.deleteProductFromStock(createProduct.sku)

        verify { productRepository.deleteOne(createProduct.sku) }
    }

    @Test
    fun `Given a delete product request, When an invalid sku is input, Then must be thrown an exception`() {
        every { productRepository.deleteOne(any()) } throws Exception()

        val result = assertThrows<ResponseStatusException> { productService.deleteProductFromStock(createProduct.sku) }

        verify { productRepository.deleteOne(createProduct.sku) }
        assert(result.reason == "Fail to DELETE product, sku not found in database!")
        assert(result.statusCode == HttpStatus.BAD_REQUEST)
    }
}
