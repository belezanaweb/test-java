package br.com.blz.produtos.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.blz.produtos.apirest.entity.EntityWarehouse;


@Repository
public interface RepositoryWarehouse extends JpaRepository<EntityWarehouse, Long>{
	
	//Warehouse findById(long sku);
	
	
}