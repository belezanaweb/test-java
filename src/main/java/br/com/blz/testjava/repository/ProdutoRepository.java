package br.com.blz.testjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.blz.testjava.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	public Produto findBySku(String sku);
	
	@Query("SELECT prod FROM Produto prod WHERE prod.sku = :sku")
	public void deleteBySku(@Param("sku") String sku);

}
