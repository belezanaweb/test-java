package br.com.blz.testjava.exception

class ProductNotFoundException (val sku: Long) : Exception("Product $sku not found")
