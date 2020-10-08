package br.com.blz.testjava.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.blz.testjava.model.Product;

@RepositoryRestResource(collectionResourceRel = "product", path = "product")
public interface ProductRestRepository extends PagingAndSortingRepository<Product, Long> { }
