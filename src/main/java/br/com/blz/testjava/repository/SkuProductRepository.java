package br.com.blz.testjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.blz.testjava.model.SkuProduct;

public interface SkuProductRepository extends JpaRepository<SkuProduct,Long> {

}
