package br.com.blz.testjava.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.blz.testjava.model.Sku;

public interface SkuRepository extends MongoRepository<Sku, String> {
	
	Sku findById(Long Id);
	Page<Sku> findAll(Pageable pageable);
}