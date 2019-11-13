package br.com.blz.testjava.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.com.blz.testjava.entity.Product;

/**
 * Classe que representa o acesso aos dados.
 * @author jmestre
 */
@Repository
public class ProdutoRepository {
	
	private Map<Integer, Product> products = new HashMap<>();
	
	public Product find(Integer sku) {
		return products.get(sku);			
	}
	
	public void delete(Integer sku) {
		products.remove(sku);			
	}
	
	public void update(Product produto) {
		save(produto);
	}
	
	public void save(Product produto) {
		products.put(produto.getSku(), produto);
	}
}
