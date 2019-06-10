package br.com.blz.testjava.service;

import br.com.blz.testjava.model.Produto;

public interface ProdutoService {

	public Produto getProduto(Long sku) throws Exception;
	
	public void delProduto(Long sku) throws Exception;
	
	public void addProduto(Produto produto) throws Exception;
	
	public void updateProduto(Produto produto) throws Exception;
	
}
