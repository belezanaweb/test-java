package br.com.blz.testjava.repository;

import br.com.blz.testjava.entity.Produto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {

    @Query("update Produto set isMarketable = 'false' where skul = :skul ")
    void deletar(Long skul);

    @Query("update Produto set isMarketable = 'true' where skul = :skul ")
    Produto recuperarProduto(Long skul);
}
