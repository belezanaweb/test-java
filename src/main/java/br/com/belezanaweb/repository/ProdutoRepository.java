package br.com.belezanaweb.repository;

import br.com.belezanaweb.domain.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProdutoRepository extends MongoRepository<Produto, Long> {

}

