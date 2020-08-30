package br.com.blz.testjava.adapters.databases

import br.com.blz.testjava.bridge.ProductManager
import br.com.blz.testjava.domain.model.Product
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface ProductDatabase : ProductManager, CrudRepository<Product, Long>