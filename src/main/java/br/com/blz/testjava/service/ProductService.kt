package br.com.blz.testjava.service

import br.com.blz.testjava.exception.ProductExistingException
import br.com.blz.testjava.exception.ProductNotFoundException
import br.com.blz.testjava.model.Product
import br.com.blz.testjava.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService {

    fun findAll(): MutableList<Product> = ProductRepository.findAll()

    fun save(product: Product) : Product = existsSku(product)
            ?.also { ProductRepository.save(it) }
            ?: throw ProductExistingException(product.sku)

    fun update(product: Product): Product = ProductRepository.findProductById(product.sku, product)
            ?.let { ProductRepository.save(product) }
            ?: throw ProductNotFoundException(product.sku)

    fun findProductById(sku: Long, product: Product): Product = ProductRepository.findProductById(sku, product)
            ?: throw ProductNotFoundException(sku)

    fun findById(sku: Long): Product = ProductRepository.findById(sku)
            ?: throw ProductNotFoundException(sku)

    fun delete(sku: Long) = ProductRepository.findById(sku)
            ?.let { ProductRepository.remove(it) }
            ?: throw ProductNotFoundException(sku)

    private fun existsSku(product: Product) : Product = ProductRepository.findById(product.sku)
            ?.let { throw ProductExistingException(product.sku) }
            ?: product
}
