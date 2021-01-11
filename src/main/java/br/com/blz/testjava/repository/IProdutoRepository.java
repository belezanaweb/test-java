package br.com.blz.testjava.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.blz.testjava.model.Produto;


@Repository
public interface IProdutoRepository {
	
	Produto save(Produto produto);
	Optional<Produto> findBySku(Long sku);
	void deleteProduto(Long sku);
	Produto editProduto(Long sku, Produto produto);

}
