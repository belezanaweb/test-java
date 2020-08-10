package br.com.blz.produtos.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.blz.produtos.apirest.entity.EntityProduto;


@Repository
public interface RepositoryProduto extends JpaRepository<EntityProduto, Long>{
	
	EntityProduto findById(long sku);
	
	
}
