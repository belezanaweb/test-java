package br.com.blz.testjava.repository;

import br.com.blz.testjava.entity.Produto;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {

    Produto findById(Long id);
}
