package br.com.blz.testjava.exception

class ProductValidationException(private val campo: String) : ProductException("Valor do $campo não é válido.")
