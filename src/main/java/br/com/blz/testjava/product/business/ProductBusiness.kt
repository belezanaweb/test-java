package br.com.blz.testjava.product.business

import br.com.blz.testjava.product.business.objects.Product
import br.com.blz.testjava.product.exception.ProductNotFoundException
import br.com.blz.testjava.product.exception.ProductSkuDuplicatedException
import br.com.blz.testjava.product.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductBusiness() {
  fun create(product: Product) : Product = checkDuplicated(product)
    .also { ProductRepository.save(it) }

  private fun checkDuplicated(product: Product) : Product = ProductRepository.get(product.sku)
    ?.let { throw ProductSkuDuplicatedException(product.sku) }
    ?: product

  fun update(product: Product): Product = ProductRepository.get(product.sku)
    ?.let { ProductRepository.save(product) }
    ?: throw ProductNotFoundException(product.sku)

  fun get(sku: Long): Product = ProductRepository.get(sku) ?: throw ProductNotFoundException(sku)
}
