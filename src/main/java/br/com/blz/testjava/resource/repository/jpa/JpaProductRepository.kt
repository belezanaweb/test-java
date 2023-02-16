package br.com.blz.testjava.resource.repository.jpa

import br.com.blz.testjava.resource.repository.entity.ProductEntity
import org.springframework.data.repository.CrudRepository

interface JpaProductRepository : CrudRepository<ProductEntity, Int>
