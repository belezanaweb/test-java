package com.boticario.testkotlin.infrastructure.adapters.repository.impl

import com.boticario.testkotlin.factory.ProductFactory.createProduct
import com.boticario.testkotlin.infrastructure.adapters.repository.ProductJpaRepository
import com.boticario.testkotlin.infrastructure.persistence.entity.ProductEntity
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.data.repository.findByIdOrNull


class ProductRepositoryImplTest {
    private val productJpaRepository = mockk<ProductJpaRepository>()
    private val productRepositoryImpl = ProductRepositoryImpl(productJpaRepository)
    private val productEntity = ProductEntity.fromDomain(createProduct)

    @Test
    fun `Given a create product input, Then must be invoke save method from jpa repository and return the created product`() {
        every { productJpaRepository.save(any()) } returns productEntity
        val result = productRepositoryImpl.save(createProduct)

        verify { productJpaRepository.save(any()) }
        assert(result == createProduct)
    }

    @Test
    fun `Given a delete product input, Then must be invoke delete method from jpa repository`() {
        every { productJpaRepository.deleteById(any()) } just runs
        productRepositoryImpl.deleteOne(createProduct.sku)

        verify { productJpaRepository.deleteById(createProduct.sku) }
    }

    @Test
    fun `Given a search entry by product, Then must be invoke the find method of the jpa repository and return the found product`() {
        every { productJpaRepository.findByIdOrNull(any()) } returns productEntity
        val result = productRepositoryImpl.findOne(createProduct.sku)

        verify { productJpaRepository.findByIdOrNull(any()) }
        assert(result == createProduct)
    }

    @Test
    fun `Given a search entry by product, When invalid sku, Then must be invoke the find method of the jpa repository and return null`() {
        every { productJpaRepository.findByIdOrNull(any()) } returns null
        val result = productRepositoryImpl.findOne(createProduct.sku)

        verify { productJpaRepository.findByIdOrNull(any()) }
        assert(result == null)
    }
}
