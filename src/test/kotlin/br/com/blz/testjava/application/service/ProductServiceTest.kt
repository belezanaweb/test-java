package br.com.blz.testjava.application.service

import br.com.blz.testjava.domain.entity.Inventory
import br.com.blz.testjava.domain.entity.Product
import br.com.blz.testjava.domain.entity.Warehouse
import br.com.blz.testjava.domain.entity.enums.WarehouseType
import br.com.blz.testjava.domain.exception.ProductAlreadyExistsException
import br.com.blz.testjava.domain.exception.ProductNoFoundException
import br.com.blz.testjava.domain.repository.ProductRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ProductServiceTest {

    private fun createProduct() = Product(
        sku = 1231,
        name = "Test",
        inventory = Inventory(
            warehouses = mutableListOf(Warehouse(
                locality = "Acre",
                quantity = 12,
                type = WarehouseType.ECOMMERCE
            ))
        )
    )

    @Test
    fun `It should save a Product`() {
        val product = createProduct()
        val service = ProductService(ProductRepository)
        val saved = service.save(product)

        assertEquals(saved.sku, 1231)
        assertEquals(service.getAll().size, 1)
        ProductRepository.products = mutableListOf()
    }

    @Test
    fun `It should get a Product by sku`() {
        val product = createProduct()
        val service = ProductService(ProductRepository)
        service.save(product)
        val saved = service.get(product.sku)

        assertEquals(saved.sku, 1231)
        assertEquals(saved.name, "Test")
        ProductRepository.products = mutableListOf()
    }

    @Test
    fun `It should update a Product by sku`() {
        val product = createProduct()
        val service = ProductService(ProductRepository)
        service.save(product)

        assertEquals(service.getAll().size, 1)

        val changed = Product(
            sku = 1231,
            name = "changed",
            inventory = Inventory(
                warehouses = mutableListOf(Warehouse(
                    locality = "Acre",
                    quantity = 12,
                    type = WarehouseType.ECOMMERCE
                ))
            )
        )
        val saved = service.update(changed.sku, changed)

        assertEquals(saved.sku, 1231)
        assertEquals(saved.name, "changed")
        assertEquals(service.getAll().size, 1)
        ProductRepository.products = mutableListOf()
    }

    @Test
    fun `It should delete a Product by sku`() {
        val product = createProduct()
        val service = ProductService(ProductRepository)
        service.save(product)

        assertEquals(service.getAll().size, 1)

        service.delete(product.sku)

        assertEquals(service.getAll().size, 0)
        ProductRepository.products = mutableListOf()
    }

    @Test
    fun `It should throw a ProductAlreadyExistsException when try to save a Product that was already saved`() {
        val product = createProduct()
        val service = ProductService(ProductRepository)
        service.save(product)

        assertThrows<ProductAlreadyExistsException> {
            service.save(product)
        }
        assertEquals(service.getAll().size, 1)
        ProductRepository.products = mutableListOf()
    }

    @Test
    fun `It should throw a ProductNoFoundException when try to get a Product that does not exists`() {
        val service = ProductService(ProductRepository)

        assertThrows<ProductNoFoundException> {
            service.get(1234)
        }
        ProductRepository.products = mutableListOf()
    }

}
