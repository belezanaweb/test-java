package br.com.blz.testjava.product.exception

import java.lang.Exception

class ProductNotFoundException(val sku: Long) : Exception("Product with sku $sku not found")
