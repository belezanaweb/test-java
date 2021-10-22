package br.com.blz.testjava.product.exceptions

import java.lang.RuntimeException

class ProductNotFoundException (message: String): RuntimeException(message)
