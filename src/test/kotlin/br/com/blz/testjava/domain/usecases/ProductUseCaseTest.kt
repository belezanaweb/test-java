package br.com.blz.testjava.domain.usecases

import br.com.blz.testjava.bridge.ProductManager
import br.com.blz.testjava.domain.exceptions.UnprocessableEntityException
import br.com.blz.testjava.domain.model.Inventory
import br.com.blz.testjava.domain.model.Product
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class ProductUseCaseTest(
     @Mock
     private val productManager: ProductManager
) {

    private val productId: Long = 1L

    @InjectMocks
    private lateinit var productUseCase: ProductUseCase

    @Test
    fun `Deve permitir cadastrar um produto novo`() {
        `when`(productManager.existsById(eq(productId))).thenReturn(false)
        val product = createProduct()
        productUseCase.create(product)
        verify(productManager, atLeastOnce()).save(eq(product) ?: product)
    }

    @Test
    fun `Nao deve permitir cadastrar um produto com SKU existente`() {
        `when`(productManager.existsById(eq(productId))).thenReturn(true)
        val product = createProduct()
        assertThrows(UnprocessableEntityException::class.java) { productUseCase.create(product) }
        verify(productManager, never()).save(any() ?: product)
    }

    @Test
    fun `Deve permitir a exclusao de um produto`() {
        productUseCase.delete(productId)
        verify(productManager, atLeastOnce()).deleteById(eq(productId))
    }

    @Test
    fun `Deve permitir buscar por SKU`() {
        `when`(productManager.findById(eq(productId))).thenReturn(Optional.of(createProduct()))
        productUseCase.read(Optional.of(productId))
        verify(productManager, atLeastOnce()).findById(eq(productId))
    }

    @Test
    fun `Deve informar quando nao encontrar um produto por SKU`() {
        assertThrows(UnprocessableEntityException::class.java) { productUseCase.read(Optional.of(productId)) }
        verify(productManager, atLeastOnce()).findById(eq(productId))
    }

    @Test
    fun `Deve permitir atualizar um produto existente`() {
        `when`(productManager.existsById(eq(productId))).thenReturn(true)
        val product = createProduct()
        productUseCase.update(productId, product)
        verify(productManager, atLeastOnce()).save(eq(product) ?: product)
    }

    @Test
    fun `Nao deve permitir atualizar um produto inexistente`() {
        `when`(productManager.existsById(eq(productId))).thenReturn(false)
        val product = createProduct()
        assertThrows(UnprocessableEntityException::class.java) { productUseCase.update(productId, product) }
        verify(productManager, never()).save(eq(product) ?: product)
    }

    private fun createProduct() = Product(productId, "L'Oréal Professionnel", mock(Inventory::class.java))

}