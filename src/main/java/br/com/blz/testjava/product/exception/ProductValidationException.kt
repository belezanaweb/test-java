package br.com.blz.testjava.product.exception

class ProductValidationException(private val field: String) : ProductBusinessException("Value of $field is not valid")
