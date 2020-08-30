package br.com.blz.testjava.domain.usecases

import br.com.blz.testjava.bridge.ProductManager
import br.com.blz.testjava.domain.model.Product
import org.springframework.stereotype.Service
import java.util.*
import br.com.blz.testjava.domain.exceptions.UnprocessableEntityException as UEException

@Service
class ProductUseCase(private val productManager: ProductManager) {

    fun create(product: Product) = if(existsProduct(product.sku)) throw UEException("product.exists") else createOrUpdate(product)

    fun delete(sku: Long) = productManager.deleteById(sku)

    fun read(sku: Optional<Long>): Any =
       if(sku.isPresent) productManager.findById(sku.get()).orElseThrow { UEException("product.not.exists") } else productManager.findAll()

    fun update(sku: Long, product: Product) {
       if(!existsProduct(sku)) throw UEException("product.not.exists")
       product.sku = sku
       createOrUpdate(product)
    }

    private fun createOrUpdate(product: Product) = productManager.save(product)

    private fun existsProduct(sku: Long) = productManager.existsById(sku)

}