package br.com.blz.testjava.domain.exception

open class ProductAlreadyExistsException(private val sku: Int): Exception("Product with sku $sku already exists")
