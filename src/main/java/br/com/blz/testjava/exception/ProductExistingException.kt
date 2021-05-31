package br.com.blz.testjava.exception

class ProductExistingException(val sku: Long) : Exception("Produto $sku já cadastrado")
