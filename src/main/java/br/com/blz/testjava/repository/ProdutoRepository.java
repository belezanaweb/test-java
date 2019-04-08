package br.com.blz.testjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.blz.testjava.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	@Query("SELECT prod FROM Produto prod WHERE prod.sku = :sku")
	public Produto findBySku(String sku);

}
