package br.com.blz.testjava.product.exception

class ProductSkuDuplicatedException(sku: Long) : ProductBusinessException("Product with sky $sku already exists")
