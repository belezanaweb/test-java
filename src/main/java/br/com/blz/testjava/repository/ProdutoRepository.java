package br.com.blz.testjava.repository;

import br.com.blz.testjava.entity.Produto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {

    @Query(value = "update Produto set isMarketable = 'false' where sku = :sku and isMarketable = true ", nativeQuery = true)
    Produto deletar(@Param("sku") Long sku);

    @Query(value = "update Produto set isMarketable = 'true' where sku = :sku and isMarketable = false", nativeQuery = true)
    Produto recuperarProduto(@Param("sku") Long sku);

    @Query(value = "select p.isMarketable from Produto p where sku = :sku", nativeQuery = true)
    boolean buscaProdutoJaDisponivel( @Param("sku") Long sku);
}
