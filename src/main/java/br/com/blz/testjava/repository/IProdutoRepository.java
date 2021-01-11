package br.com.blz.testjava.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.blz.testjava.model.Produto;
import br.com.blz.testjava.model.exception.ExistingProductException;


@Repository
public interface IProdutoRepository {
	
	Produto save(Produto produto) throws ExistingProductException;
	Optional<Produto> findBySku(Long sku);
	void deleteProduto(Long sku);
	Produto editProduto(Produto produto);

}
