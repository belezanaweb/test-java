package br.com.blz.testjava.repository;

import java.util.HashMap;
import java.util.Map;

import br.com.blz.testjava.dto.Produto;


public class ProdutoRepository {
	
	private static Map<String, Produto>  produtoMap = new HashMap<>();

	public Map<String, Produto> getProdutoMap() {
		return produtoMap;
	}

	public void setProdutoMap(Map<String, Produto> produtoMap) {
		this.produtoMap = produtoMap;
	}
		
	
	public Produto getProduto(String sku) {
		return this.produtoMap.get(sku);	
	}
	
	
	public Produto createProduto(String sku, Produto produto) {
		return this.produtoMap.put(sku,produto);	
	}
	
	public void deleteProduto(String sku) {
		 this.produtoMap.remove(sku);	
	}
	
	public void updateProduto(String sku, Produto produto) {
		produtoMap.put(sku,produto);
	
	}
}
