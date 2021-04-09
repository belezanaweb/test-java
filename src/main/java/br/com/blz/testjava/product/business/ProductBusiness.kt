package br.com.blz.testjava.product.business

import br.com.blz.testjava.product.business.objects.Product
import br.com.blz.testjava.product.exception.ProductSkuDuplicatedException
import br.com.blz.testjava.product.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductBusiness() {
  fun save(product: Product) : Product = checkDuplicated(product)
    .also { ProductRepository.save(it) }

  private fun checkDuplicated(product: Product) : Product = ProductRepository.get(product.sku)
    ?.let { throw ProductSkuDuplicatedException(product.sku) }
    ?: product

}
