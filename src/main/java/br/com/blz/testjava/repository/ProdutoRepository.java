package br.com.blz.testjava.repository;

import br.com.blz.testjava.entity.Produto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {

    @Query(value = "update Produto set isMarketable = 'false' where sku = :sku and isMarketable = false ", nativeQuery = true)
    void deletar(Long sku);

    @Query(value = "update Produto set isMarketable = 'true' where sku = :sku and isMarketable = false", nativeQuery = true)
    Produto recuperarProduto(Long sku);

    @Query(value = "select p.isMarketable from Produto where sku = :sku", nativeQuery = true)
    boolean buscaProdutoJaDisponivel(Long sku);
}
