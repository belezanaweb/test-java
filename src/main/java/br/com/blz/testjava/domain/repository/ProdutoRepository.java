package br.com.blz.testjava.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.blz.testjava.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
