package br.com.blz.testjava.exception

class ProductAlreadyExistsException(sku: Int) : Exception("Sku $sku already exists")

class ProductNotFoundException(sku: Int) : Exception("Sku $sku does not exists")
