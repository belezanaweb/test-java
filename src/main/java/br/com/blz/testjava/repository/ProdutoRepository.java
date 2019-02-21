package br.com.blz.testjava.repository;

import br.com.blz.testjava.entity.Produto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long> {

    @Modifying
    @Query(value = "update Produto set marketable = false where sku = :sku and marketable = true ", nativeQuery = true)
    Integer deletar(@Param("sku") Long sku);

    @Modifying
    @Query(value = "update Produto set marketable = true where sku = :sku and marketable = false", nativeQuery = true)
    Integer recuperarProduto(@Param("sku") Long sku);

    @Query(value = "select p.marketable from Produto p where sku = :sku", nativeQuery = true)
    boolean buscaProdutoJaDisponivel( @Param("sku") Long sku);

    Optional<Produto> findBysku(Long id);

}
